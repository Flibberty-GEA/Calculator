package com.sysgears.example.domain.members.symbols.functions;


import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.service.InputException;


import java.util.ArrayList;
import java.util.List;

/**
 * Comma is a delimiter which used to specify the boundary
 * between separate operands of a function.
 *
 * @author Yevgen Goliuk
 */
public class Comma extends Function implements BinaryFunction {
    private String value = ",";
    private String description = "— A delimiter (signified by the symbol \"" + value + "\") " +
            "used to specify the boundary between separate operands of a function." +
            "For example \"root ( 2 " + value + " 3 ) = 8\"";
    private int priority = 0;
    private int position = 0;

    /**
     * Return operands for function.
     *
     * @param expression has x - left operand
     *                   y - right operand
     * @return members list
     * @throws InputException if expression has invalid format
     */
    @Override
    public List<Member> apply(List<Member> expression) {
        Double x, y;
        try {
            x = ((Number) expression.get(getPositionFirstOperand())).getDoubleValue();
            y = ((Number) expression.get(getPositionSecondOperand())).getDoubleValue();
        } catch (ClassCastException e) {
            throw new InputException(
                    expression.get(getPositionFirstOperand()).getValue() + " " +
                            expression.get(position).getValue() + " " +
                            expression.get(getPositionSecondOperand()).getValue() + " ");
        }

        List<Member> resultList = new ArrayList<>(expression);
        resultList.remove(getPositionSecondOperand());
        resultList.remove(position);
        resultList.remove(getPositionFirstOperand());
        resultList.add(getPositionFirstOperand(), new Number(x.toString()));
        resultList.add(position, new Number(y.toString()));

        return resultList;
    }

    /**
     * Number position of the operand in the expression has dependent of delimiter position.
     *
     * @return number position of the left operand in the expression
     */
    @Override
    public int getPositionFirstOperand() {
        return position - 1;
    }

    /**
     * Number position of the operand in the expression has dependent of delimiter position.
     *
     * @return number position of the right operand in the expression
     */
    @Override
    public int getPositionSecondOperand() {
        return position + 1;
    }

    /**
     * Get a description of this delimiter.
     *
     * @return string with a description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Get the symbol.
     *
     * @return string with a value
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Get number position of this delimiter in the expression.
     *
     * @return number position of this delimiter
     */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     * Set number position of this delimiter in the expression.
     *
     * @param position - number position of this delimiter
     */
    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Get a priority for calculation.
     *
     * @return a priority number
     */
    @Override
    public int getPriority() {
        return priority;
    }
}

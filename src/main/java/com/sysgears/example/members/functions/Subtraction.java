package com.sysgears.example.members.functions;


import com.sysgears.example.members.Member;
import com.sysgears.example.members.Number;
import com.sysgears.example.members.functions.BinaryFunction;
import com.sysgears.example.members.functions.Function;
import com.sysgears.example.service.InputException;

import java.util.LinkedList;
import java.util.List;

/**
 * Subtraction is one of the four basic operations of arithmetic.
 *
 * @author Yevgen Goliuk
 */
public class Subtraction extends Function implements BinaryFunction {
    private String value = "-";
    private String description = "— Subtraction (signified by the minus sign \"" + value + "\") " +
            "is a mathematical operation that represents the operation " +
            "of removing objects from a collection. For example \"5 " + value + " 2 = 3\"";
    private int priority = 1;
    private int countOfOperands = 2;
    private int position = 0;

    /**
     * Find the result of the subtraction.
     *
     * @param expression has x - left operand of operation
     *                   y - right operand of operation
     * @return result of operation
     * @throws InputException if expression has invalid format
     */
    @Override
    public List<Member> apply(List<Member> expression) {
        Double x, y, result;
        try {
            x = ((Number) expression.get(getPositionFirstOperand())).getDoubleValue();
            y = ((Number) expression.get(getPositionSecondOperand())).getDoubleValue();
        } catch (ClassCastException e) {
            throw new InputException(
                    expression.get(getPositionFirstOperand()).getValue() + " " +
                            expression.get(position).getValue() + " " +
                            expression.get(getPositionSecondOperand()).getValue() + " ");
        }
        result = (x - y);

        List<Member> resultList = new LinkedList<>(expression);
        resultList.remove(getPositionSecondOperand());
        resultList.remove(position);
        resultList.remove(getPositionFirstOperand());
        resultList.add(getPositionFirstOperand(), new Number(result.toString()));

        return resultList;
    }

    /**
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the left operand in the expression
     */
    @Override
    public int getPositionFirstOperand() {
        return position - 1;
    }

    /**
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the right operand in the expression
     */
    @Override
    public int getPositionSecondOperand() {
        return position + 1;
    }

    /**
     * Get the minus symbol.
     *
     * @return string with a value
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Get number position of this function in the expression.
     *
     * @return number position of this function
     */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     * Set number position of this function in the expression.
     *
     * @param position - number position of this function
     */
    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Get a description of this function.
     *
     * @return string with a description
     */
    @Override
    public String getDescription() {
        return description;
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

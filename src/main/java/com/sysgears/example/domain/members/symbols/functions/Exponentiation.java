package com.sysgears.example.domain.members.symbols.functions;


import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.service.InputException;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * Exponentiation is a mathematical operation.
 *
 * @author Yevgen Goliuk
 */
public class Exponentiation extends Function implements BinaryFunction {
    private String value = "root";
    private String description = "— Exponentiation is a mathematical operation, written as \"" + value + " ( b , n )\", " +
            "involving two numbers, the base b and the exponent n. For example \"" + value + " ( 2 , 3 ) = 8\"";
    private int priority = 3;
    private int position = 0;

    /**
     * Find the result of the exponentiation.
     *
     * @param expression has base b and the exponent n
     * @return result of operation
     * @throws ArithmeticException thrown if the exponent is negative
     *                             and the power of zero (0^n, where n < 0) is undefined
     * @throws InputException      if expression has invalid format
     */
    @Override
    public List<Member> apply(List<Member> expression) {
        Double x, y, result;
        try {
            x = ((Number) expression.get(getPositionFirstOperand())).getDoubleValue();
            y = ((Number) expression.get(getPositionSecondOperand())).getDoubleValue();
        } catch (ClassCastException e) {
            throw new InputException(
                    expression.get(position).getValue() + " " +
                            expression.get(getPositionFirstOperand()).getValue() + " " +
                            expression.get(getPositionSecondOperand()).getValue() + " ");
        }

        result = Math.pow(x, y);
        if (result.equals(Infinity)) {
            throw new ArithmeticException("If the exponent is negative, the power of zero (0^n, where n < 0) " +
                    "is undefined, because division by zero is implied. ");
        }

        List<Member> resultList = new ArrayList<>(expression);
        resultList.remove(getPositionSecondOperand());
        resultList.remove(getPositionFirstOperand());
        resultList.remove(position);
        resultList.add(position, new Number(result.toString()));

        return resultList;
    }

    /**
     * Number position of base b.
     * <p>
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the base
     */
    @Override
    public int getPositionFirstOperand() {
        return position + 1;
    }

    /**
     * Number position of exponent n.
     * <p>
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the exponent n
     */
    @Override
    public int getPositionSecondOperand() {
        return position + 2;
    }

    /**
     * Get the notation of exponentiation.
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
     * Get a priority for calculation.
     *
     * @return a priority number
     */
    @Override
    public int getPriority() {
        return priority;
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
}

package com.sysgears.example.members.functions;

import com.sysgears.example.members.Member;
import com.sysgears.example.members.Number;
import com.sysgears.example.service.InputException;

import java.util.LinkedList;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

/**
 * Division is one of the four basic operations of arithmetic.
 *
 * @author Yevgen Goliuk
 */
public class Division extends Function implements BinaryFunction {
    private String value = "/";
    private String description = "— Division (signified by the symbol \"" + value + "\") " +
            "is one of the four basic operations of arithmetic. " +
            "The division of two natural numbers is the process of calculating the number " +
            "of times one number is contained within one another. For example \"20 " + value + " 5 = 4\"";
    private int priority = 2;
    private int position = 0;

    /**
     * Find the result of the division.
     *
     * @param expression has x - left operand of operation
     *                   y - right operand of operation
     * @return result of operation
     * @throws ArithmeticException thrown when expression include "divide by zero"
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
                    expression.get(getPositionFirstOperand()).getValue() + " " +
                            expression.get(position).getValue() + " " +
                            expression.get(getPositionSecondOperand()).getValue() + " ");
        }
        result = x / y;

        if (result.equals(Infinity) || result.equals(NaN)) {
            throw new ArithmeticException("Don't divide by zero.");
        }

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
     * Get the division symbol.
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

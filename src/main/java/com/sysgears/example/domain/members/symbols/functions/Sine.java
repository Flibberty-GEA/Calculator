package com.sysgears.example.domain.members.symbols.functions;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.service.InputException;

import java.util.ArrayList;
import java.util.List;

/**
 * Sine is a trigonometric function of an angle.
 *
 * @author Yevgen Goliuk
 */
public class Sine extends Function {
    private String value = "sin";
    private String description = "— In mathematics, the sine (written as \"" +
            value + " ( a )\") is a trigonometric function of an angle. " +
            "For example \"" + value + " ( 0 ) = 0\"";
    private int priority = 3;
    private int position = 0;

    /**
     * Find the sine of the angle.
     *
     * @param expression has angle
     * @return result of operation
     * @throws InputException if expression has invalid format
     */
    @Override
    public List<Member> apply(List<Member> expression) {
        Double angle;
        try {
            angle = ((Number) expression.get(getPositionOperand())).getDoubleValue();
        } catch (ClassCastException e) {
            throw new InputException(
                    expression.get(position).getValue() + " " +
                            expression.get(getPositionOperand()).getValue() + " ");
        }

        Double result = Math.sin(angle);

        List<Member> resultList = new ArrayList<>(expression);
        resultList.remove(getPositionOperand());
        resultList.remove(position);
        resultList.add(position, new Number(result.toString()));

        return resultList;
    }

    /**
     * Number position of angle a.
     * <p>
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position
     */
    public int getPositionOperand() {
        return position + 1;
    }

    /**
     * Get the notation of sine.
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

package com.sysgears.example.domain.members.symbols.functions;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.service.InputException;

import java.util.ArrayList;
import java.util.List;

/**
 * Logarithm is the inverse operation to exponentiation.
 * <p>
 * That means the logarithm of a number is the exponent
 * to which another fixed number, the base, must be raised to produce that number.
 *
 * @author Yevgen Goliuk
 */
public class Logarithm extends Function {
    private String value = "log";
    private String description = "— Logarithm written as \"" + value + " ( b , n )\", " +
            "involving two numbers, the base b and the number n. " +
            "In mathematics, the logarithm is the inverse operation to exponentiation. " +
            "That means the logarithm of a number is the exponent to which another fixed number, " +
            "the base, must be raised to produce that number. " +
            "For example \"" + value + " ( 2 , 8 ) = 3\", because 2 ^ 3 = 8";
    private int priority = 3;
    private int position = 0;


    /**
     * Find the logarithm.
     * <p>
     * log a (x) = log c (x) / log c (a)
     *
     * @param expression has number 'x'
     *                   base 'a'
     * @return result of operation
     * @throws ArithmeticException thrown when logarithm log(b,x) doesn't meet next conditions: b!=1, b>0, x>0.
     * @throws InputException      if expression has invalid format
     */
    @Override
    public List<Member> apply(List<Member> expression) {
        Double a, x;
        try {
            a = ((Number) expression.get(getPositionFirstOperand())).getDoubleValue();
            x = ((Number) expression.get(getPositionSecondOperand())).getDoubleValue();
        } catch (ClassCastException e) {
            throw new InputException(
                    expression.get(position).getValue() + " " +
                            expression.get(getPositionFirstOperand()).getValue() + " " +
                            expression.get(getPositionSecondOperand()).getValue() + " ");
        }

        if ((x > 0d) && (a > 0d) && (a != 1d)) {
            Double log10X = Math.log10(x);
            Double log10A = Math.log10(a);
            Double result = log10X / log10A;

            List<Member> resultList = new ArrayList<>(expression);
            resultList.remove(getPositionSecondOperand());
            resultList.remove(getPositionFirstOperand());
            resultList.remove(position);
            resultList.add(position, new Number(result.toString()));

            return resultList;
        } else {
            throw new ArithmeticException("Logarithm log(b,x) must meet next conditions: b!=1, b>0, x>0.");
        }

    }

    /**
     * Number position of base a.
     * <p>
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the base
     */
    public int getPositionFirstOperand() {
        return position + 1;
    }

    /**
     * Number position of number x.
     * <p>
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the exponent n
     */
    public int getPositionSecondOperand() {
        return position + 2;
    }

    /**
     * Get the notation of logarithm.
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

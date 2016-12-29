package com.sysgears.example.domain.members.symbols.functions;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;

import java.util.ArrayList;
import java.util.List;

/**
 * Addition is one of the four basic operations of arithmetic.
 *
 * @author  Yevgen Goliuk
 */
public class Addition extends Function {
    private String value = "+";
    private String description = "— Addition (signified by the plus symbol \""+value+"\") " +
            "is one of the four basic operations of arithmetic. The addition of two whole numbers " +
            "is the total amount of those quantities combined. For example \"3 "+value+" 2 = 5\"";
    private int priority = 1;
    private int position = 0;

    /**
     * Find the result of the addition.
     *
     * @param expression has x - left operand of operation
     *                     y - right operand of operation
     * @return result of operation
     */
    @Override
    public List<Member> apply(final List<Member> expression) {
        Double x = ((Number)expression.get(getPositionFirstOperand())).getDoubleValue();
        Double y = ((Number)expression.get(getPositionSecondOperand())).getDoubleValue();
        Double result =  (x + y);

        List<Member> resultList = new ArrayList<>(expression);
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
    public int getPositionFirstOperand(){
        return position-1;
    }

    /**
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the right operand in the expression
     */
    public int getPositionSecondOperand(){
        return position+1;
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
     * Get the plus symbol.
     *
     * @return string with a value
     */
    @Override
    public String getValue() {
        return value;
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

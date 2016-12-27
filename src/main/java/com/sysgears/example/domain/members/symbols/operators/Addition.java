package com.sysgears.example.domain.members.symbols.operators;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  Yevgen Goliuk
 */
public class Addition extends Function {
    private String value = "+";
    private String description = "— Addition (signified by the plus symbol \""+value+"\") " +
            "is one of the four basic operations of arithmetic. The addition of two whole numbers " +
            "is the total amount of those quantities combined. For example \"3 "+value+" 2 = 5\"";
    private int priority = 1;
    private int countOfOperands = 2;
    private int position = 0;
    /**
     *  -1 - Function Operands (before)

     *   0 - Operand Function Operand (between)
     *   1 - Operands Function (after)
     */
    private int association = 0;


    /**
     * @param expression has x - left operand of operation
     *                     y - right operand of operation
     * @return  result of operation
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

    public int getPositionFirstOperand(){
        return position-1;
    }
    public int getPositionSecondOperand(){
        return position+1;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean isOperator() {
        return true;
    }
}

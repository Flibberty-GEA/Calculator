package com.sysgears.example.domain.members.symbols.operators;


import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevgen Goliuk
 */
public class Subtraction extends Function{
    private String value = "-";
    private String description = "— Subtraction (signified by the minus sign \""+value+"\") " +
            "is a mathematical operation that represents the operation " +
            "of removing objects from a collection. For example \"5 "+value+" 2 = 3\"";
    private int priority = 1;
    private int countOfOperands = 2;
    private int position = 0;

    @Override
    public List<Member> apply(List<Member> expression) {
        Double x = ((Number)expression.get(getPositionFirstOperand())).getDoubleValue();
        Double y = ((Number)expression.get(getPositionSecondOperand())).getDoubleValue();
        Double result =  (x - y);

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
    public String getValue() {
        return value;
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
    public boolean isOperator() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}

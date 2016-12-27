package com.sysgears.example.domain.members.symbols.functions;


import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevgen Goliuk
 */
public class Exponentiation extends Function {
    private String value = "root";
    private String description = "— Exponentiation is a mathematical operation, written as \""+value+" ( b , n )\", " +
            "involving two numbers, the base b and the exponent n. For example \""+value+" ( 2 , 3 ) = 8\"";
    private int priority = 3;
    private int countOfOperands = 2;
    private int position = 0;

    @Override
    public List<Member> apply(List<Member> expression) {
        Double x = ((Number)expression.get(getPositionFirstOperand())).getDoubleValue();
        Double y = ((Number)expression.get(getPositionSecondOperand())).getDoubleValue();
        Double result = Math.pow(x, y);

        List<Member> resultList = new ArrayList<>(expression);
        resultList.remove(getPositionSecondOperand());
        resultList.remove(getPositionFirstOperand());
        resultList.remove(position);
        resultList.add(position, new Number(result.toString()));

        return resultList;
    }

    public int getPositionFirstOperand(){
        return position+1;
    }
    public int getPositionSecondOperand(){
        return position+2;
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
    public boolean isOperator() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

package com.sysgears.example.domain.members.symbols.operators;



import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevgen Goliuk
 */
public class Division extends Function {
    private String value = "/";
    private String description = "— Division (signified by the plus symbol \""+value+"\") " +
            "is one of the four basic operations of arithmetic. " +
            "The division of two natural numbers is the process of calculating the number " +
            "of times one number is contained within one another. For example \"20 "+value+" 5 = 4\"";
    private int priority = 2;
    private int countOfOperands = 2;
    private int position = 0;

    @Override
    public List<Member> apply(List<Member> expression) {
        Double result =  (Double.valueOf(expression.get(getPositionFirstOperand()).getValue())) /
                (Double.valueOf(expression.get(getPositionSecondOperand()).getValue()));

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
//public static final Logger log = LogManager.getLogger(Division.class);
}

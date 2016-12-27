package com.sysgears.example.domain.members.symbols.operators;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevgen Goliuk
 */
public class Logarithm extends Function {
    private String value = "log";
    private String description = "— Logarithm written as \""+value+" ( b , n )\", " +
            "involving two numbers, the base b and the number n. " +
            "In mathematics, the logarithm is the inverse operation to exponentiation. " +
            "That means the logarithm of a number is the exponent to which another fixed number, " +
            "the base, must be raised to produce that number. " +
            "For example \""+value+" ( 2 , 8 ) = 3\", because 2 ^ 3 = 8";
    private int priority = 3;
    private int countOfOperands = 2;
    private int position = 0;


    /**
     * log a (b) = log c (b) / log c (a)
     *
     * @param expression has number 'b'
     *                     base 'a'
     * @return  result of operation
     */
    @Override
    public List<Member> apply(List<Member> expression) {
        Double x = ((Number)expression.get(getPositionFirstOperand())).getDoubleValue();
        Double y = ((Number)expression.get(getPositionSecondOperand())).getDoubleValue();
        Double b = Math.log10(y);
        Double a = Math.log10(x);
        Double result = b / a;

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

package com.sysgears.example.domain.members.symbols.functions;


import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Yevgen Goliuk
 */
public class Comma extends Function {
    private String value = ",";
    private int priority = 1;
    private int countOfOperands = 2;
    private int position = 0;
    private String description = "— A delimiter (signified by the plus symbol \""+value+"\") " +
            "used to specify the boundary between separate operands of a function." +
            "For example \"root ( 2 "+value+" 3 ) = 8\"";

    @Override
    public List<Member> apply(List<Member> expression) {
        Double x = ((Number)expression.get(getPositionFirstOperand())).getDoubleValue();
        Double y = ((Number)expression.get(getPositionSecondOperand())).getDoubleValue();
//        Double result =  (x + y);

        List<Member> resultList = new ArrayList<>(expression);
        resultList.remove(getPositionSecondOperand());
        resultList.remove(position);
        resultList.remove(getPositionFirstOperand());
        resultList.add(getPositionFirstOperand(), new Number(x.toString()));
        resultList.add(position, new Number(y.toString()));

        return resultList;
    }

    public int getPositionFirstOperand(){
        return position-1;
    }
    public int getPositionSecondOperand(){
        return position+1;
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
}

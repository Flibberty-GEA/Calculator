package com.sysgears.example.domain.members.symbols.operators;

/**
 * @author  Yevgen Goliuk
 */
public class Addition extends Operation {

    private String value = "+";
    private int priority = 1;

    /**
     * @param operands has x - left operand of operation
     *                     y - right operand of operation
     * @return  result of operation
     */
    @Override
    public Double apply(Double... operands) {
        return operands[0] + operands[1];
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

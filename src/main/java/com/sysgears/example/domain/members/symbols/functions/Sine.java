package com.sysgears.example.domain.members.symbols.functions;

/**
 * @author Yevgen Goliuk
 */
public class Sine{
    private String value = "S";
    private int priority = 4;

    public String getValue() {
        return value;
    }

    /**
     * @param operands has x - left operand of operation
     *                     y - right operand of operation
     * @return  result of operation
     */
    public Double apply(Double... operands) {
        return Math.sin(operands[0]);
    }

    public boolean isOperator() {
        return true;
    }

    public int getPriority() {
        return priority;
    }
}

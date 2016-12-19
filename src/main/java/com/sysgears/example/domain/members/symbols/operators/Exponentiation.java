package com.sysgears.example.domain.members.symbols.operators;

/**
 * @author  Yevgen Goliuk
 */
public class Exponentiation extends Operation {

    private String value = "^";
    private int priority = 3;

    /**
     * @param x left operand of operation
     * @param y right operand of operation
     * @return  result of operation
     */
    @Override
    public Double apply(Double x, Double y) {
        return Math.pow(x, y);
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

package com.sysgears.example.domain.symbols.operators;

/**
 * @author  Yevgen Goliuk
 */
public class Divide extends Operator {

    private String value = "/";
    private int priority = 2;

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * @param x left operand of operation
     * @param y right operand of operation
     * @return  result of operation
     */
    @Override
    public Double apply(Double x, Double y) {
        return x / y;
    }
}

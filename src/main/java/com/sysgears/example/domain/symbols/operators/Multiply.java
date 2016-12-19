package com.sysgears.example.domain.symbols.operators;

/**
 * @author  Yevgen Goliuk
 */
public class Multiply extends Operator {

    private String value = "*";
    private int priority = 2;
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
    public Double apply(final Double x, final Double y) {
        return x * y;
    }
    @Override
    public boolean isOperator() {
        return true;
    }
}

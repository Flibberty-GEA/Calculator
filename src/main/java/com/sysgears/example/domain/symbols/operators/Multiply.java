package com.sysgears.example.domain.symbols.operators;

/**
 * Created by yevgen on 15.12.16.
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
    @Override
    public Double apply(final Double x, final Double y) {
        return x * y;
    }
    @Override
    public boolean isOperator() {
        return true;
    }
}

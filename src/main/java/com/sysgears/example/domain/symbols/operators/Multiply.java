package com.sysgears.example.domain.symbols.operators;

/**
 * Created by yevgen on 15.12.16.
 */
public class Multiply extends Operator {
    private Character value = '*';
    private int priority = 2;

    public Character getValue() {
        return value;
    }

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

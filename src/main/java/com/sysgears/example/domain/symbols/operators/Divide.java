package com.sysgears.example.domain.symbols.operators;

/**
 * Created by yevgen on 15.12.16.
 */
public class Divide extends Operator {

    private Character value = '/';
    private int priority = 2;

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public Character getValue() {
        return value;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Double apply(Double x, Double y) {
        return x / y;
    }
}

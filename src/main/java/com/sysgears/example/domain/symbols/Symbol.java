package com.sysgears.example.domain.symbols;

/**
 * Created by yevgen on 15.12.16.
 */
public abstract class Symbol {
    public abstract boolean isOperator();
    public abstract Character getValue();
    public abstract int getPriority();
}

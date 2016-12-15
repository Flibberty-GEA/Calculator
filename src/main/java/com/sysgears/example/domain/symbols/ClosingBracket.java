package com.sysgears.example.domain.symbols;

/**
 * Created by yevgen on 15.12.16.
 */
public class ClosingBracket extends Symbol {
    private Character value = ')';

    public Character getValue() {
        return value;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isOperator() {
        return false;
    }
}

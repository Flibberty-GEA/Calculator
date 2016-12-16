package com.sysgears.example.domain.symbols;

/**
 * Created by yevgen on 15.12.16.
 */
public class OpeningBracket extends Symbol {

    private String value = "(";

    @Override
    public String getValue() {
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

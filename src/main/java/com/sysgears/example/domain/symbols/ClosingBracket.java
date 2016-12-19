package com.sysgears.example.domain.symbols;

/**
 * @author  Yevgen Goliuk
 */
public class ClosingBracket extends Symbol {
    private String value = ")";

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

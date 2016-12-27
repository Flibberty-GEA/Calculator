package com.sysgears.example.domain.members.symbols;

/**
 * Parentheses in mathematics signify a different precedence of operators.
 *
 * @author  Yevgen Goliuk
 */
public class ClosingBracket extends Symbol {
    private String value = ")";
    private String description = "— Closing bracket (signified by the plus symbol \")\") " +
            "marks the end of a region of expression. Parentheses in mathematics " +
            "signify a different precedence of operators";
    private int position = 0;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isOperator() {
        return false;
    }
}

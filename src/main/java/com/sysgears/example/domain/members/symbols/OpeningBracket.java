package com.sysgears.example.domain.members.symbols;

/**
 * Parentheses in mathematics signify a different precedence of operators.
 *
 * @author  Yevgen Goliuk
 */
public class OpeningBracket extends Symbol {
    private String value = "(";
    private String description = "— Opening bracket (signified by the plus symbol \""+value+"\") " +
            "marks the start of a region of expression. Parentheses in mathematics " +
            "signify a different precedence of operators. For example: \"2 + 3 * 4 + 14\", " +
            "since the multiplication is done before the addition. However, \"(2 + 3) * 4 + 20\", " +
            "because the parentheses override normal precedence, causing the addition to be done first";
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

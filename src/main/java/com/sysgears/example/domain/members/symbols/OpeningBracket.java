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
    private int priority = 0;

    /**
     * Get the opening bracket symbol.
     *
     * @return string with a value
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Get a priority for calculation.
     *
     * @return a priority number
     */
    @Override
    public int getPriority() {
        return 0;
    }

    /**
     * Set number position of this delimiter in the expression.
     *
     * @param position - number position of this delimiter
     */
    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Get a description of this delimiter.
     *
     * @return string with a description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Check this member of the expression. Is it an operator?
     *
     * @return true if this member of the expression is an operator
     */
    @Override
    public boolean isOperator() {
        return false;
    }
}

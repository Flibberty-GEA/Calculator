package com.sysgears.example.domain.members.symbols;

/**
 * Parentheses in mathematics signify a different precedence of operators.
 *
 * @author Yevgen Goliuk
 */
public class ClosingBracket extends Symbol {
    private String value = ")";
    private String description = "— Closing bracket (signified by the symbol \")\") " +
            "marks the end of a region of expression. Parentheses in mathematics " +
            "signify a different precedence of operators";
    private int position = 0;
    private int priority = 0;

    /**
     * Get the closing bracket symbol.
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
        return priority;
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

package com.sysgears.example.domain.members;

/**
 * Member of expression.
 * That can be operators, operands, brackets or other symbols.
 *
 * @author Yevgen Goliuk
 */
public interface Member {

    /**
     * Get the symbol or notation.
     *
     * @return string with a value of a member
     */
    String getValue();

    /**
     * Set number position of this member in the expression.
     *
     * @param position - number position of this member
     */
    public void setPosition(int position);

}

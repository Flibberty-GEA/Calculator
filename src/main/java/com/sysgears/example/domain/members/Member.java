package com.sysgears.example.domain.members;

/**
 * Member of expression.
 * That can be operators, operands, brackets or other symbols.
 *
 * @author  Yevgen Goliuk
 */
public interface Member {

    /**
     * Get the symbol or notation.
     *
     * @return string with a value of a member
     */
    String getValue();
}

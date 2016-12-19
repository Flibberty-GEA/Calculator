package com.sysgears.example.domain.members;

/**
 * Member of expression.
 * That can be operators, operands, brackets or other symbols.
 *
 * @author  Yevgen Goliuk
 */
public interface Member {

    /**
     * @return a symbol or a value of a member
     */
    String getValue();
}

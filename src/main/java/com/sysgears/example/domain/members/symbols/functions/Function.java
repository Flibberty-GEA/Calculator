package com.sysgears.example.domain.members.symbols.functions;


import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.symbols.Symbol;
import com.sysgears.example.service.InputException;

import java.util.List;

/**
 * Function is a calculation from zero or more input values (called operands) to an output value.
 *
 * @author Yevgen Goliuk
 */
public abstract class Function extends Symbol {

    /**
     * Find the result of the function.
     *
     * @param expression
     * @return result of operation
     * @throws ArithmeticException
     * @throws InputException      if expression has invalid format
     */
    public abstract List<Member> apply(final List<Member> expression);

    /**
     * Get number position of this function in the expression.
     *
     * @return number position of this function
     */
    public abstract int getPosition();

    /**
     * Check this member of the expression. Is it an operator?
     *
     * @return true if this member of the expression is an operator
     */
    @Override
    public boolean isOperator() {
        return true;
    }
}

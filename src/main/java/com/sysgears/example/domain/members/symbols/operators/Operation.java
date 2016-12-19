package com.sysgears.example.domain.members.symbols.operators;


import com.sysgears.example.domain.members.symbols.Symbol;

/**
 * Operation is a calculation from zero or more input values (called operands) to an output value.
 *
 * @author  Yevgen Goliuk
 */
public abstract class Operation extends Symbol {

    /**
     * @param x left operand of operation
     * @param y right operand of operation
     * @return  result of operation
     */
    public abstract Double apply(final Double x, final Double y);
}

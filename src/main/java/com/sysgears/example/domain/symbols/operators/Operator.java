package com.sysgears.example.domain.symbols.operators;


import com.sysgears.example.domain.symbols.Symbol;

/**
 * @author  Yevgen Goliuk
 */
public abstract class Operator extends Symbol {

    /**
     * @param x left operand of operation
     * @param y right operand of operation
     * @return  result of operation
     */
    public abstract Double apply(final Double x, final Double y);
}

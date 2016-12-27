package com.sysgears.example.domain.members.symbols.operators;

import com.sysgears.example.service.ParserRPN;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Yevgen Goliuk
 */
public class Logarithm extends Operation{

//    public static final Logger log = LogManager.getLogger(Operation.class);

    private String value = "L";
    private int priority = 3;

    @Override
    public String getValue() {
        return value;
    }

    /**
     * log a (b) = log c (b) / log c (a)
     *
     * @param operands has x - number 'b'
     *                     y - base 'a'
     * @return  result of operation
     */
    @Override
    public Double apply(Double... operands) {
        Double b = Math.log10(operands[1]);
        Double a = Math.log10(operands[0]);
        return b / a;
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}

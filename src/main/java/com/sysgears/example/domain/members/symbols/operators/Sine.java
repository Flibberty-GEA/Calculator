package com.sysgears.example.domain.members.symbols.operators;

/**
 * @author Yevgen Goliuk
 */
public class Sine extends Operation {

    private String value = "S";
    private int priority = 4;

    @Override
    public String getValue() {
        return value;
    }

    /**
     * @param operands has x - left operand of operation
     *                     y - right operand of operation
     * @return  result of operation
     */
    @Override
    public Double apply(Double... operands) {
        return Math.sin(operands[0]);
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority;
    }
//public static final Logger log = LogManager.getLogger(Sine.class);
}

package com.sysgears.example.domain.members.symbols.functions;

/**
 * Have to implements with an unary functions
 *
 * @author yevgen
 */
public interface UnaryFunction {
    /**
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position
     */
    int getPositionOperand();
}

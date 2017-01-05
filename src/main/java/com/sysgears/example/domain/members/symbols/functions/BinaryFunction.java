package com.sysgears.example.domain.members.symbols.functions;

/**
 * Have to implements with an binary functions
 *
 * @author yevgen
 */
public interface BinaryFunction {
    /**
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the first operand in the expression
     */
    int getPositionFirstOperand();
    /**
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the second operand in the expression
     */
    int getPositionSecondOperand();
}

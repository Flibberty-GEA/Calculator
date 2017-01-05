package com.sysgears.example.domain.members.symbols.functions;

/**
 * @author yevgen
 */
public interface TernaryFunction {
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
    /**
     * Number position of the operand in the expression has dependent of function position.
     *
     * @return number position of the third operand in the expression
     */
    int getPositionThirdOperand();
}

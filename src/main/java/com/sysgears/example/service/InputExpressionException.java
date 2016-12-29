package com.sysgears.example.service;

/**
 * Signals about exceptions that can be thrown during the calculation
 * because of an incorrect input expression.
 *
 * @author  Yevgen Goliuk
 */
public class InputExpressionException extends RuntimeException {
    /**
     * Default message of InputExpressionException.
     */
    private static final String MES = "Input arguments are incorrect: ";

    /**
     * Constructs InputExpressionException without a detail message.
     */
    public InputExpressionException() {
        super(MES);
    }

    /**
     * Constructs InputExpressionException a with detail message.
     *
     * @param message the detail message
     */
    public InputExpressionException(final String message) {
        super(MES + message);
    }

    /**
     * Constructs InputExpressionException with a detail message and a specified cause.
     *
     * @param message the detail message
     * @param cause   the specified cause of InputExpressionException
     */
    public InputExpressionException(final String message, final Throwable cause) {
        super(MES + message, cause);
    }

    /**
     * Constructs InputExpressionException with a specified cause.
     *
     * @param cause the specified cause of InputExpressionException
     */
    public InputExpressionException(final Throwable cause) {
        super(MES, cause);
    }
}

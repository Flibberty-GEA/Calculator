package com.sysgears.example.service;

/**
 * Signals about exceptions that can be thrown during the calculation
 * because of an incorrect input expression.
 *
 * @author Yevgen Goliuk
 */
public class InputException extends RuntimeException {
    /**
     * Default message of InputException.
     */
    private static final String DEFAULT_MESSAGE = "Input arguments are incorrect: ";

    /**
     * Constructs InputException without a detail message.
     */
    public InputException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs InputException a with detail message.
     *
     * @param message the detail message
     */
    public InputException(final String message) {
        super(DEFAULT_MESSAGE + message);
    }

    /**
     * Constructs InputException with a detail message and a specified cause.
     *
     * @param message the detail message
     * @param cause   the specified cause of InputException
     */
    public InputException(final String message, final Throwable cause) {
        super(DEFAULT_MESSAGE + message, cause);
    }

    /**
     * Constructs InputException with a specified cause.
     *
     * @param cause the specified cause of InputException
     */
    public InputException(final Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}

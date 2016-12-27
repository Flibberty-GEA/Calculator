package com.sysgears.example.service;

/**
 * Signals about exceptions that can be thrown because of an incorrect input command.
 *
 * @author  Yevgen Goliuk
 */
public class InputCommandException extends RuntimeException {
    /**
     * Default message of InputCommandException.
     */
    private static final String MES = "Input command are incorrect. ";

    /**
     * Constructs InputCommandException without a detail message.
     */
    public InputCommandException() {
        super(MES);
    }

    /**
     * Constructs InputCommandException a with detail message.
     *
     * @param message the detail message
     */
    public InputCommandException(final String message) {
        super(MES + message);
    }

    /**
     * Constructs InputCommandException with a detail message and a specified cause.
     *
     * @param message the detail message
     * @param cause   the specified cause of InputCommandException
     */
    public InputCommandException(String message, Throwable cause) {
        super(MES + message, cause);
    }

    /**
     * Constructs InputCommandException with a specified cause.
     *
     * @param cause the specified cause of InputCommandException
     */
    public InputCommandException(Throwable cause) {
        super(MES, cause);
    }
}

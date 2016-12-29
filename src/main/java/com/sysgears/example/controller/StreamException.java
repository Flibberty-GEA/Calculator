package com.sysgears.example.controller;

/**
 * Signals that an I/O exception has occurred.
 * <p>
 * Throws if something is wrong with connection with user.
 *
 * @author Yevgen Goliuk
 */
public class StreamException extends RuntimeException {

    /**
     * Default message for StreamException.
     */
    private static final String DEFAULT_MESSEGE = "Connection is lost. ";

    /**
     * Constructs StreamException without detail message.
     */
    public StreamException() {
        super(DEFAULT_MESSEGE);
    }

    /**
     * Constructs StreamException with detail message.
     *
     * @param message the detail message
     */
    public StreamException(final String message) {
        super(DEFAULT_MESSEGE + message);
    }

    /**
     * Constructs StreamException with detail message and specified cause.
     *
     * @param message the detail message
     * @param cause   the specified cause of StreamException
     */
    public StreamException(final String message, final Throwable cause) {
        super(DEFAULT_MESSEGE + message, cause);
    }

    /**
     * Constructs StreamException with specified cause.
     *
     * @param cause the specified cause of StreamException
     */
    public StreamException(final Throwable cause) {
        super(DEFAULT_MESSEGE, cause);
    }
}

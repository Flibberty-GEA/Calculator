package com.sysgears.example.exceptions;

/**
 * Signals about exceptions that can be thrown because of an incorrect input command.
 *
 * @author  Yevgen Goliuk
 */
public class InputCommandException extends RuntimeException {
    public InputCommandException(String message) {
        super(message);
    }
}

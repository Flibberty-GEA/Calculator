package com.sysgears.example.exceptions;

/**
 * Signals about exceptions that can be thrown during the calculation
 * because of an incorrect input expression.
 *
 * @author  Yevgen Goliuk
 */
public class InputExpressionException extends RuntimeException {
    public InputExpressionException(final String message) {
        super(message);
    }
}

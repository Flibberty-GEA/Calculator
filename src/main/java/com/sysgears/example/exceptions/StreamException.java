package com.sysgears.example.exceptions;

import java.io.IOException;

/**
 * Signals that an I/O exception has occurred.
 *
 * @author  Yevgen Goliuk
 */
public class StreamException extends IOException {
    public StreamException(final String message) {
        super(message);
    }
}

package com.sysgears.example.exceptions;

import java.io.IOException;

/**
 * @author  Yevgen Goliuk
 */
public class StreamException extends IOException {
    //IOException if an I/O error occurs

    public StreamException(String message) {
        super(message);
    }
}

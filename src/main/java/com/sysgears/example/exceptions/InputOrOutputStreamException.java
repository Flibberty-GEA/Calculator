package com.sysgears.example.exceptions;

import java.io.IOException;

/**
 * Created by yevgen on 13.12.16.
 */
public class InputOrOutputStreamException extends IOException {
    //IOException if an I/O error occurs

    public InputOrOutputStreamException(String message) {
        super(message);
    }
}

package com.sysgears.example.controller;

import com.sysgears.example.exceptions.StreamException;

import java.io.*;

/**
 * Class for contact with user by console.
 */
public class StreamController {

    /**
     * Reader of input data
     */
    BufferedReader reader;

    /**
     * Writer of output data
     */
    BufferedWriter writer;

    public BufferedWriter getWriter() {
        return writer;
    }

    /**
     * Initialize buffered reader and writer from input and output stream
     *
     * @param input  source of input data
     * @param output destination of output data
     */
    public StreamController(final InputStream input, final OutputStream output) {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.writer = new BufferedWriter(new OutputStreamWriter(output));
    }

    /**
     * @return next line from reader
     * @throws IOException if an I/O error occurs
     */
    public String getRequest() throws StreamException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new StreamException("Hello from CONTROLLER "+e.getMessage().toString());
        }
    }

    /**
     * @param response is a message or result of request
     * @throws IOException if an I/O error occurs
     */
    public void sendResponse (final String response) throws StreamException {
        try {
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new StreamException("Hello from CONTROLLER "+e.getMessage().toString());
        }

    }


}

package com.sysgears.example.controller;

import java.io.*;

/**
 * Provides streams control, for example, to contact the user via the console
 *
 * @author Yevgen Goliuk
 */
public class StreamController {

    BufferedReader reader;
    BufferedWriter writer;
    private boolean isOpen;

    /**
     * @return buffered character-output stream that uses a default-sized output buffer.
     */
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
        this.isOpen = true;
    }

    /**
     * @return next line from reader
     * @throws StreamException if an I/O error occurs
     */
    public String getRequest() throws StreamException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new StreamException("Sorry, I can't read your expression. Please try again. ");
        }
    }

    /**
     * @param response is a message or result of request
     * @throws StreamException if an I/O error occurs
     */
    public void sendResponse(final String response) throws StreamException {
        try {
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new StreamException("Sorry, a connection broken. Please try again. ");
        }
    }

    /**
     * Check is StreamController open.
     *
     * @return true if controller is open
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Closes StreamController.
     *
     * @throws StreamException if an controller error occurs
     */
    public void closeController() {
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new StreamException("Could not close input or output stream. ", e);
        } finally {
            isOpen = false;
        }
    }
}

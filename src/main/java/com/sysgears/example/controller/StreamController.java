package com.sysgears.example.controller;

import java.io.*;

/**
 * Provides streams control, for example, to contact the user via the console.
 *
 * @author Yevgen Goliuk
 */
public class StreamController {

    BufferedReader bufferReader;
    BufferedWriter bufferedWriter;
    private boolean isOpen;

    /**
     * Initialize StreamController with bufferReader and bufferedWriter from input and output stream.
     *
     * @param input  source of input data
     * @param output destination of output data
     */
    public StreamController(final InputStream input, final OutputStream output) {
        this.bufferReader = new BufferedReader(new InputStreamReader(input));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
        this.isOpen = true;
    }

    /**
     * Get user's request from console.
     *
     * @return next line from bufferReader
     * @throws StreamException if an I/O error occurs
     */
    public String getRequest() throws StreamException {
        try {
            return bufferReader.readLine();
        } catch (IOException e) {
            throw new StreamException("Sorry, I can't read your expression. Please try again. ");
        }
    }

    /**
     * Send response to the user by the console.
     *
     * @param response is a message or result of request
     * @throws StreamException if an I/O error occurs
     */
    public void sendResponse(final String response) throws StreamException {
        try {
            bufferedWriter.write(response);
            bufferedWriter.newLine();
            bufferedWriter.flush();
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
            bufferReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new StreamException("Could not close input or output stream. ", e);
        } finally {
            isOpen = false;
        }
    }
}

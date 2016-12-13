package com.sysgears.example.controller;

import com.sysgears.example.exceptions.InputOrOutputStreamException;

import java.io.*;

/**
 * Class for contact with user.
 * The Controller accepts input and converts it to commands for the model or view.
 * It can send commands to the model to update the model's state.
 * It can also send commands to its associated view (console output) to change the view's presentation of the model.
 */
public class Controller {

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
    public Controller(final InputStream input, final OutputStream output) {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.writer = new BufferedWriter(new OutputStreamWriter(output));
    }

    /**
     * @return next line from reader
     * @throws IOException if an I/O error occurs
     */
    public String getRequest() throws InputOrOutputStreamException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new InputOrOutputStreamException("Hello from CONTROLLER "+e.getMessage().toString());
        }
    }

    /**
     * @param response is a message or result of request
     * @throws IOException if an I/O error occurs
     */
    public void sendResponse (final String response) throws InputOrOutputStreamException {
        try {
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new InputOrOutputStreamException("Hello from CONTROLLER "+e.getMessage().toString());
        }

    }


}

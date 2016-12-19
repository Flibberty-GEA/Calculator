package com.sysgears.example.controller;

import com.sysgears.example.exceptions.StreamException;

import java.io.*;

/**
 * The {@code StreamController} provides streams control,
 * for example, to contact the user via the console
 *
 * @author  Yevgen Goliuk
 */
public class StreamController {

    BufferedReader reader;
    BufferedWriter writer;

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
    }

    /**
     * @return next line from reader
     * @throws StreamException if an I/O error occurs
     */
    public String getRequest() throws StreamException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new StreamException("Hello from StreamController "+e.getMessage().toString());
        }
    }

    /**
     * @param response is a message or result of request
     * @throws StreamException if an I/O error occurs
     */
    public void sendResponse (final String response) throws StreamException {
        try {
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new StreamException("Hello from StreamController "+e.getMessage().toString());
        }
    }

    /**
     * @param fileName the <tt>File</tt> to read from
     * @return all info from <tt>File</tt>
     *
     * @throws StreamException  if the file does not exist, is a directory rather than a regular file,
     * or for some other reason cannot be opened for reading.
     */
    public String readFromFile (final String fileName) throws StreamException {
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            in = new BufferedReader(new FileReader(fileName));
            String lineText;
            while ((lineText = in.readLine()) != null) {
                sb.append(lineText+"\n");
            }
            in.close();
        } catch (FileNotFoundException e) {
            throw new StreamException("Hello from StreamController "+e.getMessage().toString());
        } catch (IOException e) {
            throw new StreamException("Hello from StreamController "+e.getMessage().toString());
        }
        return sb.toString();
    }
}

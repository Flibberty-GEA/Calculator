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
            throw new StreamException("Hello from StreamController "+e.getMessage().toString());
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
            throw new StreamException("Hello from StreamController "+e.getMessage().toString());
        }
    }

    /**
     * @param fileName the <tt>File</tt> to read from
     * @return all info from <tt>File</tt>
     * @exception  FileNotFoundException  if the file does not exist,
     *                   is a directory rather than a regular file,
     *                   or for some other reason cannot be opened for
     *                   reading.
     * @throws IOException if an I/O error occurs
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

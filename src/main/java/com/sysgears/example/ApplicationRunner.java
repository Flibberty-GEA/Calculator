package com.sysgears.example;

import com.sysgears.example.controller.RequestController;

import java.io.IOException;

/**
 * Main class of program
 */
public class ApplicationRunner {

    /**
     * Entrance point to program
     *
     * @param args command line input arguments
     */
    public static void main(String[] args) {
        try{
            /* initialize main execution object and start the program */
            new RequestController().run();
        } catch (Exception e) {
            /* print exception message if IO exception has been thrown */
            System.out.println(e.getMessage());
        } catch (Throwable t) {
            /* print exception message if any throwable has been thrown */
            System.out.println(t.getMessage());
        }
    }
}

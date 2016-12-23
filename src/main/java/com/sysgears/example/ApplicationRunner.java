package com.sysgears.example;

import com.sysgears.example.controller.RequestController;

/**
 * Main class of program
 *
 * @author  Yevgen Goliuk
 */
public class ApplicationRunner {

    /**
     * Entrance point to program.
     *
     * @param args command line input arguments
     */
    public static void main(String[] args) {
        try{
            /* initialize main execution object and start the program */
            new RequestController().run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}

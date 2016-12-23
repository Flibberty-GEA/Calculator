package com.sysgears.example;

import com.sysgears.example.controller.RequestController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Main class of program
 *
 * @author  Yevgen Goliuk
 */
public class ApplicationRunner {

    public static final Logger log = LogManager.getLogger(ApplicationRunner.class);

    /**
     * Entrance point to program.
     *
     * @param args command line input arguments
     */
    public static void main(String[] args) {
        log.info("Start program.");

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

package com.sysgears.example;

import com.sysgears.example.controller.RequestController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Main class of program.
 *
 * @author  Yevgen Goliuk
 */
public class ApplicationRunner {
//    public static final Logger log = LogManager.getLogger(ApplicationRunner.class);

//  2       2
//  1       3 - 2
//  Nan     0 / 0
//  -2.3(3) 2 + -13 / 3
//  3       log ( 2 , 8 )
//  8       root ( 2 , 3 )
//  2       ( 2 )
//  -1      ( 2 + -3 )
//  21      ( 2 + ( 4 * 3 ) + 4 ) + 3
//  22      2 + 4 * 3 + root ( 2 , 3 )
//  20      ( 2 + 4 ) * 3 + root ( 2 , 3 ) + ( 1 + -7 )
    /**
     * Entrance point to program.
     *
     * @param args command line input arguments
     */
    public static void main(String[] args) {
//        log.info("Start program.");
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

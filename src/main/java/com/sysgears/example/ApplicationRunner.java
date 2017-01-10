package com.sysgears.example;

import com.sysgears.example.controller.RequestController;

/**
 * Main class of program.
 *
 * @author Yevgen Goliuk
 */
public class ApplicationRunner {

// data for checking
//  2       2
//  1       3 - 2
//  NaN     0 / 0
//  NaN     root ( 0 , -3 )
//  NaN     log ( -1 , 2 )
//  -2.3(3) 2 + -13 / 3
//  -0.988  sin ( 30 )
//  3       log ( 2 , 8 )
//  8       root ( 2 , 3 )
//  8       root ( 2 , log 2 8 )
//  8       root ( 2 , log ( 2 , 8 ) )
//  3       log ( 2 , root ( 2 , 3 ) )
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
        try {
            new RequestController().run();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

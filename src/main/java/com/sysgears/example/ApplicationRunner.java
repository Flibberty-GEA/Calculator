package com.sysgears.example;


import com.sysgears.example.controller.StreamController;
import com.sysgears.example.controller.RequestController;

import java.io.IOException;

/**
 *
 */
public class ApplicationRunner {


    private static void initialize(){

    }
    private ApplicationRunner() {
        initialize();
    }

    public static void main(String[] args) {
        ApplicationRunner applicationRunner = new ApplicationRunner();
        // добиться того чтобы исключение не вылетало
        try{
            new RequestController().run();
        } catch (IOException e) {
            /* print exception message if IO exception has been thrown */
            System.out.println(e.getMessage());
        } catch (Throwable t) {
            /* print exception message if any throwable has been thrown */
            System.out.println(t.getMessage());
        }
    }
}

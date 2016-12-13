package com.sysgears.example;


import com.sysgears.example.controller.Controller;
import com.sysgears.example.exceptions.InputOrOutputStreamException;
import com.sysgears.example.service.ApplicationService;

import java.io.IOException;

/**
 *
 */
public class Application {

//    public void init(){}



    public static void main(String[] args) {
//        final Controller controller = new Controller(System.in, System.out);
//        new ApplicationService(controller).run();

        // добиться того чтобы исключение не вылетало
        try{
            final Controller controller = new Controller(System.in, System.out);
            new ApplicationService(controller).run();
        } catch (IOException e) {
            /* print exception message if IO exception has been thrown */
            System.out.println(e.getMessage());
        } catch (Throwable t) {
            /* print exception message if any throwable has been thrown */
            System.out.println(t.getMessage());
        }
    }
}

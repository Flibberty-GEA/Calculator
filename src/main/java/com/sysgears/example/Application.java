package com.sysgears.example;


import com.sysgears.example.controller.Controller;
import com.sysgears.example.service.ApplicationService;

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
        }catch (Exception e){
        }
    }
}

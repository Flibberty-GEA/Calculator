package com.sysgears.example;

import com.sysgears.example.controller.Controller;
import org.junit.Test;

import java.io.IOException;


public class TestForController {

    @Test
    public void testController() throws IOException {
        Controller controller = new Controller(System.in, System.out);
        controller.sendResponse("Hello!");
//        String expression = controller.getRequest();
//        System.out.println(expression);
    }

}

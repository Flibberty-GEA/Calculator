package com.sysgears.example;

import com.sysgears.example.controller.StreamController;
import org.junit.Test;

import java.io.IOException;


public class TestForController {

    @Test
    public void testController() throws IOException {
        StreamController streamController = new StreamController(System.in, System.out);
        streamController.sendResponse("Hello!");
//        String expression = streamController.getRequest();
//        System.out.println(expression);
    }

}

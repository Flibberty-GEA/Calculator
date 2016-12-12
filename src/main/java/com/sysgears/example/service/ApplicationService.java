package com.sysgears.example.service;

import com.sysgears.example.controller.Controller;
import com.sysgears.example.domain.Calculator;
import com.sysgears.example.input.Command;
import com.sysgears.example.exceptions.InputException;

import java.io.IOException;

/**
 *
 */
public class ApplicationService {

    /**
     * object for connect to user
     */
    private final Controller controller;
    /**
     * @param controller object for connect to user
     */
    public ApplicationService(final Controller controller) {
        this.controller = controller;
    }

    /**
     * Executes user's command
     *
     * @throws IOException if something is wrong with connection to user
     */
    public void run() throws IOException {
        /* send greetings to user */
        controller.sendResponse("Hello!");

        while (true) {
            /* request user for expression */
            controller.sendResponse("Input expression to calculate OR 'EXIT' to quit program.");
            String expression = controller.getRequest();
                try {
                    /* send goodbyes and close program */
                    if (expression.toUpperCase().equals(Command.EXIT.name())){
                        controller.sendResponse("Good bye!");
                        controller.getWriter().close();
                        return;
                    } else {

                        expression = Calculator.toRPN(expression);
                        /* calculate expression result, save to history and send to user */
                        controller.sendResponse(String.valueOf(Calculator.calculate(expression)));
                    }
                } catch (InputException | NumberFormatException | ArithmeticException e) {
                /* send error message if some calculator exception has been caught */
                    controller.sendResponse("Incorrect expression. Try again. Example: -2 + 3*4 - 7/-5");
                }catch (Exception e){
                    controller.sendResponse(e.getMessage());
                }
        }
    }
}

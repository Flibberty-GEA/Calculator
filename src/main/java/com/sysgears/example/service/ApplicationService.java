package com.sysgears.example.service;

import com.sysgears.example.controller.Controller;
import com.sysgears.example.domain.Calculator;
import com.sysgears.example.domain.History;
import com.sysgears.example.input.Command;
import com.sysgears.example.exceptions.InputArgumentsException;

import java.io.IOException;

/**
 *
 */
public class ApplicationService {

    /**
     * object for connect to user
     */
    private final Controller controller;
    private final Calculator calculator;
    private final History history;
    /**
     * @param controller object for connect to user
     */
    public ApplicationService(final Controller controller) {
        this.controller = controller;
        this.calculator = new Calculator();
        this.history = new History();
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
                    } else if (expression.toUpperCase().equals(Command.HISTORY.name())) {
                        controller.sendResponse(history.getAllRecords().toString());
                    } else if (expression.toUpperCase().equals(Command.UNIQUE_HISTORY.getName())) {
                        controller.sendResponse(history.getUniqueRecords().toString());
                    } else {
                        String result = String.valueOf(calculator.calculate(expression));
                        history.saveResult(result);
                        /* calculate expression result, save to history and send to user */
                        controller.sendResponse(result);
                    }
                } catch (InputArgumentsException | NumberFormatException | ArithmeticException e) {
                /* send error message if some calculator exception has been caught */
                    controller.sendResponse("Incorrect expression. "+e.getMessage()+" Try again. Example: (-2) + 3*4 - 7/(-5^(-2))");
                }catch (Exception e){
                    controller.sendResponse(e.getMessage());
                }
        }
    }
}

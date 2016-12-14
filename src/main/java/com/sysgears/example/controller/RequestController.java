package com.sysgears.example.controller;

import com.sysgears.example.service.Calculator;
import com.sysgears.example.exceptions.InputCommandException;
import com.sysgears.example.exceptions.InputExpressionException;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Class for control dispatch.
 *
 * RequestController, accepts input and converts it to commands for the Calculator or to response for the output.
 */
public class RequestController {

    /* initialize controller for contact with user by console */
    private final StreamController streamController = new StreamController(System.in, System.out);
    /* initialize calculator */
    private final Calculator calculator = new Calculator();

    /**
     * Executes user's command.
     *
     * @throws Exception if something is wrong with execution
     */
    public void run() throws Exception {
        streamController.sendResponse("Hello!");

        while (true) {
            /* request user for expression */
            streamController.sendResponse("Input expression to calculate \n"+
                    "OR 'HISTORY' / 'UNIQUE HISTORY' to see history of results \nOR 'EXIT' to quit program .");
            String expression = streamController.getRequest();
            expression = expression.trim();
                try {
                    /* send goodbyes and close program */
                    if (expression.toUpperCase().equals(RequestCommand.EXIT.name())){
                        streamController.sendResponse("Good bye!");
                        streamController.getWriter().close();
                        return;
                    }
                    /* send all history to user if expression is command "history" */
                    else if (expression.toUpperCase().equals(RequestCommand.HISTORY.name())) {
                        streamController.sendResponse(calculator.getAllRecords());
                    }
                    /* send unique history to user if expression is command "history unique" */
                    else if (expression.toUpperCase().equals(RequestCommand.UNIQUE_HISTORY.getName())) {
                        streamController.sendResponse(calculator.getUniqueRecords());
                    }
                    /* calculate expression result, save to history and send to user */
                    else {
                        String result = String.valueOf(calculator.calculate(expression));
                        streamController.sendResponse(result);
                    }
                } catch (InputExpressionException | NumberFormatException | ArithmeticException e) {
                    /* send error message if some calculator exception has been caught */
                    streamController.sendResponse("Incorrect expression. "+e.getMessage()+" Try again.\nIf you have negative number then you should use brackets. \nExample: (-2) + 3*4 - 7/(-5^(-2))\n");
                } catch (InputCommandException e){
                    /* send error message if some command exception has been caught */
                    streamController.sendResponse("Incorrect command. "+e.getMessage());
                } catch (NoSuchElementException | StringIndexOutOfBoundsException e){
                    /* send error message if some calculator exception has been caught */
                    streamController.sendResponse("Нет операндов ");
                } catch (Exception e){
                    streamController.sendResponse(e.getMessage()+" <- RequestController cath Exception "+e.getClass());
                }
        }
    }
}

package com.sysgears.example.controller;

import com.sysgears.example.service.Calculator;
import com.sysgears.example.exceptions.InputCommandException;
import com.sysgears.example.exceptions.InputExpressionException;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 *
 */
public class RequestController {

    /**
     * object for connect to user
     */
    private final StreamController streamController;
    private final Calculator calculator;
    /**
     *
     */
    public RequestController() {
        this.streamController = new StreamController(System.in, System.out);
        this.calculator = new Calculator();
    }

    /**
     * Executes user's command
     *
     * @throws IOException if something is wrong with connection to user
     */
    public void run() throws IOException {
        /* send greetings to user */
        streamController.sendResponse("Hello!");

        while (true) {
            /* request user for expression */
            streamController.sendResponse("Input expression to calculate OR 'EXIT' to quit program.");
            String expression = streamController.getRequest();
            expression = expression.trim();
                try {
                    /* send goodbyes and close program */
                    if (expression.toUpperCase().equals(RequestCommand.EXIT.name())){
                        streamController.sendResponse("Good bye!");
                        streamController.getWriter().close();
                        return;
                    } else if (expression.toUpperCase().equals(RequestCommand.HISTORY.name())) {
                        streamController.sendResponse(calculator.getAllRecords());
                    } else if (expression.toUpperCase().equals(RequestCommand.UNIQUE_HISTORY.getName())) {
                        streamController.sendResponse(calculator.getUniqueRecords());
                    } else {
                        String result = String.valueOf(calculator.calculate(expression));
                        /* calculate expression result, save to history and send to user */
                        streamController.sendResponse(result);
                    }
                } catch (InputExpressionException | NumberFormatException | ArithmeticException e) {
                /* send error message if some calculator exception has been caught */
                    streamController.sendResponse("Incorrect expression. "+e.getMessage()+" Try again.\nIf you have negative number then you should use brackets. \nExample: (-2) + 3*4 - 7/(-5^(-2))\n");
                }catch (InputCommandException e){
                    streamController.sendResponse("Incorrect command. "+e.getMessage());
                } catch (NoSuchElementException | StringIndexOutOfBoundsException e){
                    streamController.sendResponse("Нет операндов ");
                } catch (Exception e){
//                    e.printStackTrace();
                    streamController.sendResponse(e.getMessage()+" <- RequestController cath Exception "+e.getClass());
                }
        }
    }
}

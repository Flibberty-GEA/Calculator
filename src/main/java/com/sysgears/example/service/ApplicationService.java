package com.sysgears.example.service;

import com.sysgears.example.controller.Controller;
import com.sysgears.example.domain.Calculator;
import com.sysgears.example.domain.History;
import com.sysgears.example.exceptions.InputCommandException;
import com.sysgears.example.input.Command;
import com.sysgears.example.exceptions.InputExpressionException;

import java.io.IOException;
import java.util.NoSuchElementException;

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
            expression = expression.trim();
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
                } catch (InputExpressionException | NumberFormatException | ArithmeticException e) {
                /* send error message if some calculator exception has been caught */
                    controller.sendResponse("Incorrect expression. "+e.getMessage()+" Try again.\nIf you have negative number then you should use brackets. \nExample: (-2) + 3*4 - 7/(-5^(-2))\n");
                }catch (InputCommandException e){
                    controller.sendResponse("Incorrect command. "+e.getMessage());
                } catch (NoSuchElementException | StringIndexOutOfBoundsException e){
                    controller.sendResponse("Нет операндов ");
                } catch (Exception e){
//                    e.printStackTrace();
                    controller.sendResponse(e.getMessage()+" <- ApplicationService cath Exception "+e.getClass());
                }
        }
    }
}

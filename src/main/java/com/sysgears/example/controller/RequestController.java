package com.sysgears.example.controller;

import com.sysgears.example.service.Calculator;
import com.sysgears.example.exceptions.InputCommandException;
import com.sysgears.example.exceptions.InputExpressionException;
import com.sysgears.example.service.HistoryDAO;

import java.util.NoSuchElementException;

/**
 * Accepts input and converts it to expression for the Calculator or to response for the output.
 *
 * @author  Yevgen Goliuk
 */
public class RequestController {

    private final StreamController streamController = new StreamController(System.in, System.out);
    private final HistoryDAO historyDAO = new HistoryDAO();
    private final Calculator calculator = new Calculator(historyDAO);


    /**
     * Executes user's command.
     *
     * @throws Exception if something is wrong with streamController
     */
    public void run() throws Exception {
        streamController.sendResponse("Hello!");

        while (true) {
            streamController.sendResponse("\nInput expression to calculate \n"+
                    "OR 'EXIT' to quit program .");
            String inputRequest = streamController.getRequest();
            inputRequest = determineRequest(inputRequest);
                try {
                    if (Command.isCommand(inputRequest)){
                        Command command = Command.valueOf(inputRequest);
                        command.apply(streamController, historyDAO);
                    } else {
                        String result = String.valueOf(calculator.calculate(inputRequest));
                        streamController.sendResponse("RESULT: " + result);
                    }
                } catch (InputExpressionException | NumberFormatException | ArithmeticException e) {
                    /* send error message if some calculator exception has been caught */
                    streamController.sendResponse("Incorrect expression. "+e.getMessage()+" \nPlease try again or enter the \"HELP\".");
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

    /**
     * Check the query contains a command or an expression
     *
     * @param inputRequest
     * @return command or expression
     */
    private String determineRequest(final String inputRequest){
        String result = inputRequest.trim().toUpperCase().replace("UNIQUE HISTORY", "UNIQUE_HISTORY");
            for (Command command : Command.values()) {
                if (!result.contains(command.name())) {
                    continue;
                } else {
                    result = command.name();
                    return result;
                }
            }
        return result;
    }
}

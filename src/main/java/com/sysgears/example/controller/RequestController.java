package com.sysgears.example.controller;


import com.sysgears.example.service.InputException;
import com.sysgears.example.history.HistoryDAO;
import com.sysgears.example.service.MyExecutor;


/**
 * Accepts input and converts it to expression for the Calculator or to response for the output.
 *
 * @author Yevgen Goliuk
 */
public class RequestController {

    private final StreamController streamController = new StreamController(System.in, System.out);
    private final HistoryDAO historyDAO = new HistoryDAO();
    private final MyExecutor myExecutor = new MyExecutor(historyDAO);

    /**
     * Executes user's command.
     */
    public void run() {
        streamController.sendResponse("Hello!");

        while (streamController.isOpen()) {
            streamController.sendResponse("\nInput expression to calculate \n" +
                    "OR 'EXIT' to quit program .");
            String inputRequest = streamController.getRequest();
            try {
                if (Command.isCommand(inputRequest)) {
                    Command command = Command.valueOf(Command.changeCommandDelimiter(inputRequest));
                    command.apply(streamController, historyDAO);
                } else {
                    String result = String.valueOf(myExecutor.execute(inputRequest.toLowerCase()));
                    streamController.sendResponse("RESULT: " + result);
                }
            } catch (InputException | NumberFormatException | ArithmeticException e) {
                    /* send error message if some calculator exception has been caught */
                streamController.sendResponse("Incorrect expression. " + e.getMessage() +
                        " \nPlease try again or enter the \"HELP\".");
            } catch (Exception e) {
                streamController.sendResponse("RequestController cath Exception " + e.getClass());
            }
        }
    }


}

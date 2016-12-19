package com.sysgears.example.controller;


import com.sysgears.example.service.HistoryDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The {@code Command}
 *
 * @author  Yevgen Goliuk
 */
public enum Command {

    /**
     * command for exit from program
     */
    EXIT{
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse("Good bye!");
            streamController.getWriter().close();
            return;
        }
    },

    /**
     * command for seeing unique results history
     */
    UNIQUE_HISTORY{
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse(historyDAO.getUnique());
        }
    },

    /**
     * command for seeing full results history
     */
    HISTORY{
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse(historyDAO.getAll());
        }
    },

    /**
     * command for seeing help info
     */
    HELP{
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            String helpInfo = streamController.readFromFile("src/main/resources/help.txt");
            streamController.sendResponse(helpInfo);
        }
    };

    /**
     * @param streamController for contact with user by console
     * @throws IOException
     */
    abstract void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException;
}
package com.sysgears.example.controller;


import com.sysgears.example.domain.HelpInfo;
import com.sysgears.example.history.HistoryDAO;

import java.io.IOException;

/**
 * The commands entered by the client
 *
 * @author Yevgen Goliuk
 */
public enum Command {

    /**
     * Command for exit from program.
     */
    EXIT {
        void apply(final StreamController streamController,
                   final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse("Good bye!");
            streamController.getWriter().close();
            return;
        }
    },

    /**
     * command for seeing unique results history
     */
    UNIQUE_HISTORY {
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse(historyDAO.getUnique());
        }
    },

    /**
     * command for seeing full results history
     */
    HISTORY {
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse(historyDAO.getAll());
        }
    },

    /**
     * command for seeing help info
     */
    HELP {
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            String helpInfo = new HelpInfo().getHelpInfo();
            streamController.sendResponse(helpInfo);
        }
    };

    /**
     * добавить
     *
     * @param streamController for contact with user by console
     * @param historyDAO       for use or update history
     * @throws IOException
     */
    abstract void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException;

    /**
     * @param request
     * @return true if request is command
     * @throws IOException
     */
    public static boolean isCommand(final String request) {
        for (Command command : Command.values()) {
            if (!request.contains(command.name())) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }
}
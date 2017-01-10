package com.sysgears.example.controller;


import com.sysgears.example.service.HelpInfoBuilder;
import com.sysgears.example.history.HistoryDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        private String description = this.name() + " - show usage information";

        /**
         * Apply command "EXIT".
         *
         * Close streams controller and finish the application work.
         *
         * @param streamController for contact with user by console
         * @param historyDAO       for use or update history
         */
        @Override
        void apply(final StreamController streamController,
                   final HistoryDAO historyDAO) {
            streamController.sendResponse("Good bye!");
            streamController.closeController();
        }

        @Override
        String getDescription() {
            return description;
        }

    },

    /**
     * Command for seeing unique results history.
     */
    UNIQUE_HISTORY {
        private String description = this.name().replace("_", " ") + " - show unique records from storage";

        /**
         * Apply command "UNIQUE_HISTORY".
         *
         * Send history about unique calculations on a console.
         *
         * @param streamController for contact with user by console
         * @param historyDAO       for use or update history
         */
        @Override
        void apply(final StreamController streamController, final HistoryDAO historyDAO) {
            streamController.sendResponse(historyDAO.getUnique());
        }

        @Override
        String getDescription() {
            return description;
        }
    },

    /**
     * Command for seeing full results history.
     */
    HISTORY {
        private String description = this.name() + " - show all records from storage";

        /**
         * Apply command "HISTORY".
         *
         * Send history about calculations on a console.
         *
         * @param streamController for contact with user by console
         * @param historyDAO       for use or update history
         */
        @Override
        void apply(final StreamController streamController, final HistoryDAO historyDAO) {
            streamController.sendResponse(historyDAO.getAll());
        }

        @Override
        String getDescription() {
            return description;
        }
    },

    /**
     * Command for seeing help info.
     */
    HELP {
        private String description = this.name() + " - show usage information";

        @Override
        void apply(final StreamController streamController, final HistoryDAO historyDAO) {
            String helpInfo = new HelpInfoBuilder().getHelpInfo();
            streamController.sendResponse(helpInfo);
        }

        @Override
        String getDescription() {
            return description;
        }
    };

    /**
     * Apply the user's command.
     *
     * @param streamController for contact with user by console
     * @param historyDAO       for use or update history
     */
    abstract void apply(final StreamController streamController, final HistoryDAO historyDAO);

    abstract String getDescription();

    /**
     * Check if input request is a command.
     *
     * @param inputRequest
     * @return true if request is command
     * @throws IOException
     */
    public static boolean isCommand(final String inputRequest) {
        String request = changeCommandDelimiter(inputRequest);
        for (Command command : Command.values()) {
            if (!request.equals(command.name())) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Change delimiter of commands from " " to "_".
     *
     * @param inputRequest
     * @return command with correct delimiter
     */
    public static String changeCommandDelimiter(final String inputRequest) {
        String result = inputRequest.trim().toUpperCase().replaceAll("( )+", "_");
        return result;
    }

    /**
     * Get descriptions for all commands.
     *
     * @return a list with all command's descriptions
     */
    public static List<String> allDescriptions() {
        Command[] values = Command.values();
        List<String> resultList = new ArrayList<>();
        for (int index = 0; index < values.length; index++) {
            resultList.add(values[index].getDescription());
        }
        return resultList;
    }
}
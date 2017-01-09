package com.sysgears.example.controller;


import com.sysgears.example.domain.HelpInfo;
import com.sysgears.example.domain.members.symbols.Symbol;
import com.sysgears.example.history.HistoryDAO;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        String description = this.name() + " - show usage information";

        @Override
        void apply(final StreamController streamController,
                   final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse("Good bye!");
            streamController.closeController();
            return;
        }

        @Override
        String getDescription() {
            return description;
        }

    },

    /**
     * command for seeing unique results history
     */
    UNIQUE_HISTORY {
        String description = this.name().replace("_", " ") + " - show unique records from storage";

        @Override
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse(historyDAO.getUnique());
        }

        @Override
        String getDescription() {
            return description;
        }
    },

    /**
     * command for seeing full results history
     */
    HISTORY {
        String description = this.name() + " - show all records from storage";

        @Override
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            streamController.sendResponse(historyDAO.getAll());
        }

        @Override
        String getDescription() {
            return description;
        }
    },

    /**
     * command for seeing help info
     */
    HELP {
        String description = this.name() + " - show usage information";

        @Override
        void apply(final StreamController streamController, final HistoryDAO historyDAO) throws IOException {
            String helpInfo = new HelpInfo().getHelpInfo();
            streamController.sendResponse(helpInfo);
        }

        @Override
        String getDescription() {
            return description;
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

    abstract String getDescription();

    /**
     * @param inputRequest
     * @return true if request is command
     * @throws IOException
     */
    public static boolean isCommand(final String inputRequest) {
        String request = determineRequest(inputRequest);
        for (Command command : Command.values()) {
            if (!request.contains(command.name())) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the query contains a command or an expression
     *
     * @param inputRequest
     * @return command or expression
     */
    public static String determineRequest(final String inputRequest) {
        String result = inputRequest.trim().toUpperCase().replaceAll("( )+", "_");
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

    public static List<String> allDescriptions(){
        Command[] values = Command.values();
        List<String> resultList = new ArrayList<>();
        for (int index = 0; index < values.length; index++) {
            resultList.add(values[index].getDescription());
        }
        return resultList;
    }
}
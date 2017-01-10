package com.sysgears.example.service;

import com.sysgears.example.controller.Command;
import com.sysgears.example.members.Symbol;

import java.util.List;

/**
 * Service to building help info.
 *
 * @author Yevgen Goliuk
 */
public class HelpInfoBuilder {
    private final String header = "HELP INFO\n" +
            "You can use following command: " + valuesCommands() + ".\n" + descriptionsCommands();

    private final String body = "You can use operators and delimiters as " + valuesFunctions() + ":\n" + descriptionsFunctions();

    private final String footer = "You should use space between all operands, operators, functions and delimiters.\n" +
            "Example: 2 + 4 * 3 + root ( 2 , 3 )";

    /**
     * Get values for all commands of application.
     *
     * @return a string with all command's values
     */
    public String valuesCommands() {
        String result = "";
        Command[] values = Command.values();
        for (int index = 0; index < values.length - 1; index++) {
            result += "\"" + values[index].name() + "\", ";
        }
        result += "\"" + values[values.length-1].name() + "\"";

        return result.replace("_", " ");
    }

    /**
     * Get descriptions for all commands of application.
     *
     * @return a string with all command's descriptions
     */
    public String descriptionsCommands() {
        String result = "";
        List<String> list = Command.allDescriptions();
        for (int index = 0; index < list.size() - 1; index++) {
            result += list.get(index) + ";\n";
        }
        result += list.get(list.size() - 1) + ".";
        return result;
    }

    /**
     * Get values for all functions of application.
     *
     * @return a string with all functions's values
     */
    public String valuesFunctions() {
        String result = "";
        List<String> list = Symbol.values();
        for (int index = 0; index < list.size() - 1; index++) {
            result += "\"" + list.get(index) + "\", ";
        }
        result += "\"" + list.get(list.size() - 1) + "\"";

        return result;
    }

    /**
     * Get descriptions for all functions of application.
     *
     * @return a string with all functions's descriptions
     */
    public String descriptionsFunctions() {
        String result = "";
        List<String> list = Symbol.descriptions();
        for (int index = 0; index < list.size() - 1; index++) {
            result += list.get(index) + ";\n";
        }
        result += list.get(list.size() - 1) + ".";
        return result;
    }

    /**
     * Build help info.
     *
     * @return a string with a header, a body and a footer wich include all help info
     */
    public String getHelpInfo() {
        return header + "\n\n" + body + "\n\n" + footer;
    }
}

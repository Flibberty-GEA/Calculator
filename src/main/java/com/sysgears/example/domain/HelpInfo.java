package com.sysgears.example.domain;

import com.sysgears.example.controller.Command;
import com.sysgears.example.domain.members.symbols.Symbol;

import java.util.List;

/**
 * @author Yevgen Goliuk
 */
public class HelpInfo {
    private final String header = "HELP INFO\n" +
            "You can use following command: " + valuesCommands() + ".\n" + descriptionsCommands();

    private final String body = "You can use operators and delimiters as " + valuesOperators() + ":\n" + descriptionsOperators();

    private final String footer = "You should use space between all operands, operators, functions and delimiters.\n" +
            "Example: 2 + 4 * 3 + root ( 2 , 3 )";

    public String valuesCommands() {
        String result = "";
        Command[] values = Command.values();
        for (int index = 0; index < values.length - 1; index++) {
            result += "\"" + values[index].name() + "\", ";
        }
        result += "\"" + values[values.length-1].name() + "\"";

        return result.replace("_", " ");
    }

    public String descriptionsCommands() {
        String result = "";
        List<String> list = Command.allDescriptions();
        for (int index = 0; index < list.size() - 1; index++) {
            result += list.get(index) + ";\n";
        }
        result += list.get(list.size() - 1) + ".";
        return result;
    }

    public String valuesOperators() {
        String result = "";
        List<String> list = Symbol.values();
        for (int index = 0; index < list.size() - 1; index++) {
            result += "\"" + list.get(index) + "\", ";
        }
        result += "\"" + list.get(list.size() - 1) + "\"";

        return result;
    }

    public String descriptionsOperators() {
        String result = "";
        List<String> list = Symbol.descriptions();
        for (int index = 0; index < list.size() - 1; index++) {
            result += list.get(index) + ";\n";
        }
        result += list.get(list.size() - 1) + ".";
        return result;
    }

    public String getHelpInfo() {
        return header + "\n\n" + body + "\n\n" + footer;
    }

}

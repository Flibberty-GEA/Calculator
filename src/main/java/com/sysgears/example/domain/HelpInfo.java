package com.sysgears.example.domain;

import com.sysgears.example.domain.members.symbols.Symbol;

import java.util.List;

/**
 * @author Yevgen Goliuk
 */
public class HelpInfo {
    private final String header = "HELP INFO\n" +
            "You can use following command:\n" +
            "help - show usage information\n" +
            "history - show all records from storage\n" +
            "unique history - show unique records from storage\n" +
            "exit - command for exit from program";

    private final String body = "You can use operators and delimiters as " + values() + ":\n" + descriptions();

    private final String footer = "You should use space between all operands, operators, functions and delimiters.\n" +
            "Example: 2 + 4 * 3 + root ( 2 , 3 )";

    public String values() {
        String result = "";
        List<String> list = Symbol.values();
        for (int index = 0; index < list.size() - 1; index++) {
            result += "\"" + list.get(index) + "\", ";
        }
        result += "\"" + list.get(list.size() - 1) + "\"";

        return result;
    }

    public String descriptions() {
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

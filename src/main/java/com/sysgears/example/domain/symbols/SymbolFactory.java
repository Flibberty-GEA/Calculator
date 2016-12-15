package com.sysgears.example.domain.symbols;


import com.sysgears.example.domain.symbols.*;
import com.sysgears.example.domain.symbols.operators.*;

/**
 * Created by yevgen on 15.12.16.
 */
public class SymbolFactory {

    public Symbol createInstance(Character type) throws Exception {
        switch (type) {
            case '+':
                return new Plus();
            case '-':
                return new Minus();
            case '*':
                return new Multiply();
            case '/':
                return new Divide();
            case '^':
                return new Power();
            case '(':
                return new OpeningBracket();
            case ')':
                return new ClosingBracket();
            default:
                throw new Exception();
        }

    }
}

package com.sysgears.example.domain.members.symbols;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.exceptions.InputExpressionException;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The class Symbol and its subclasses are a form of Member
 * that indicates the properties that define the role and behavior of the members of expression.
 *
 * @author  Yevgen Goliuk
 */
public abstract class Symbol implements Member {

    /**
     * @return true if a member of expression is operator
     */
    public abstract boolean isOperator();

    /**
     * @return priority of an expression member
     */
    public abstract int getPriority();

    /**
     * Takes over the selection of the appropriate instance of a Symbol.
     *
     * @param value of a member
     * @return an instance of the symbol with a coincident value
     * @throws Exception if something is wrong with the symbol
     */
    public static Symbol createInstance(final Character value) throws Exception {
        String symbolValue = String.valueOf(value);
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subclasses = reflections.getSubTypesOf(Symbol.class);
        Map<String, Symbol> values = new HashMap<>();
        for (Class clazz:subclasses) {
            try {
                Symbol symbol = (Symbol)clazz.newInstance();
                values.put(symbol.getValue(), symbol);
            } catch (InstantiationException | IllegalAccessException e) {
                continue;
                //if nothing to created, return null and try to create Number in ParserRPN.toRPN()
            }
        }
        if (values.containsKey(symbolValue)){
            return values.get(symbolValue);
        } else throw new Exception();
    }
}

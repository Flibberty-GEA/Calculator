package com.sysgears.example.members;

import com.sysgears.example.service.InputException;
import org.reflections.Reflections;

import java.util.*;

/**
 * The class Symbol and its subclasses are a form of Member
 * that indicates the properties that define the role and behavior of the members of expression.
 *
 * @author Yevgen Goliuk
 */
public abstract class Symbol implements Member {

    /**
     * Takes over the selection of the appropriate instance of a Symbol.
     * <p>
     * This method uses reflections.
     *
     * @param value    of a member
     * @param position of this member in the expression
     * @return an instance of the symbol with a coincident value
     * @throws InputException if something is wrong with the symbol
     */
    public final static Symbol createInstance(final String value, final int position) {
        String symbolValue = value;
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subclasses = reflections.getSubTypesOf(Symbol.class);
        Map<String, Symbol> values = new HashMap<>();
        for (Class clazz : subclasses) {
            try {
                Symbol symbol = (Symbol) clazz.newInstance();
                values.put(symbol.getValue(), symbol);
            } catch (InstantiationException | IllegalAccessException e) {
                continue;
            }
        }
        if (values.containsKey(symbolValue)) {
            Symbol symbol = values.get(symbolValue);
            symbol.setPosition(position);
            return symbol;
        } else throw new InputException("Symbol \'" + symbolValue + "\" is incorrect.");
    }

    /**
     * Get all symbols or notations for functions and delimiters.
     *
     * @return strings list for values
     */
    public static List<String> values() {
        List<String> resultList = new ArrayList<>();
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subclasses = reflections.getSubTypesOf(Symbol.class);
        for (Class clazz : subclasses) {
            try {
                Symbol symbol = (Symbol) clazz.newInstance();
                resultList.add(symbol.getValue());
            } catch (InstantiationException | IllegalAccessException e) {
                continue;
            }
        }
        return resultList;
    }

    /**
     * Get descriptions of all functions and delimiters.
     *
     * @return strings list for values
     */
    public static List<String> descriptions() {
        List<String> resultList = new ArrayList<>();
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subclasses = reflections.getSubTypesOf(Symbol.class);
        for (Class clazz : subclasses) {
            try {
                Symbol symbol = (Symbol) clazz.newInstance();
                resultList.add(symbol.getDescription());
            } catch (InstantiationException | IllegalAccessException e) {
                continue;
            }
        }
        return resultList;
    }

    /**
     * Get a priority for calculation.
     *
     * @return priority of an expression member
     */
    public abstract int getPriority();

    /**
     * Get a description of this function.
     *
     * @return string with a description
     */
    public abstract String getDescription();

}

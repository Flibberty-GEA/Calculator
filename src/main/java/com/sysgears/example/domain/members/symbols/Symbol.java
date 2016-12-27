package com.sysgears.example.domain.members.symbols;

import com.sysgears.example.domain.members.Member;
import org.reflections.Reflections;

import java.util.*;

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

    public abstract void setPosition(int position);

    public abstract String getDescription();

    /**
     * Takes over the selection of the appropriate instance of a Symbol.
     *
     * @param value of a member
     * @return an instance of the symbol with a coincident value
     * @throws Exception if something is wrong with the symbol
     */
    public static Symbol createInstance(final String value, final int position) throws Exception {
        String symbolValue = value;
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subclasses = reflections.getSubTypesOf(Symbol.class);
        Map<String, Symbol> values = new HashMap<>();
        for (Class clazz:subclasses) {
            try {
                Symbol symbol = (Symbol)clazz.newInstance();
                values.put(symbol.getValue(), symbol);
            } catch (InstantiationException | IllegalAccessException e) {
                continue;
            }
        }
        if (values.containsKey(symbolValue)){
            Symbol symbol = values.get(symbolValue);
            symbol.setPosition(position);
            return symbol;
        } else throw new Exception();
    }

    public static List<String> values(){
        List<String> resultList = new ArrayList<>();
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subclasses = reflections.getSubTypesOf(Symbol.class);
        for (Class clazz:subclasses) {
            try {
                Symbol symbol = (Symbol)clazz.newInstance();
                resultList.add(symbol.getValue());
            } catch (InstantiationException | IllegalAccessException e) {
                continue;
            }
        }
        return resultList;
    }

    public static List<String> descriptions(){
        List<String> resultList = new ArrayList<>();
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subclasses = reflections.getSubTypesOf(Symbol.class);
        for (Class clazz:subclasses) {
            try {
                Symbol symbol = (Symbol)clazz.newInstance();
                resultList.add(symbol.getDescription());
            } catch (InstantiationException | IllegalAccessException e) {
                continue;
            }
        }
        return resultList;
    }

}

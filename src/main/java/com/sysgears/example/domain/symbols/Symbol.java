package com.sysgears.example.domain.symbols;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The class {@code Symbol} and its subclasses are a form of {@code Member}
 * that indicates the properties that define the role and behavior of the members of expression
 *
 * @author  Yevgen Goliuk
 */
public abstract class Symbol implements Member {
    public abstract boolean isOperator();
    public abstract int getPriority();
    public static Symbol createInstance(Character type) throws Exception {
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subTypes = reflections.getSubTypesOf(Symbol.class);
        Map<String, Class> values = new HashMap<>();
        for (Class clazz:subTypes) {
            try {
                Symbol s = (Symbol)clazz.newInstance();
                values.put(s.getValue(), clazz);
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
        if (values.containsKey(String.valueOf(type))){
            try {
                Class clazz = values.get(String.valueOf(type));
                Symbol c = (Symbol)clazz.newInstance();
                return c;
            } catch (Exception e) {
                throw new Exception();
            }
        } else throw new Exception();
    }
}

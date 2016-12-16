package com.sysgears.example.domain.symbols;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yevgen on 15.12.16.
 */
public abstract class Symbol {
    public abstract boolean isOperator();
    public abstract Character getValue();
    public abstract int getPriority();
    public static Symbol createInstance(Character type) throws Exception {
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subTypes = reflections.getSubTypesOf(Symbol.class);
        Map<Character, Class> values = new HashMap<>();
        for (Class clazz:subTypes) {
            try {
                Symbol s = (Symbol)clazz.newInstance();
                values.put(s.getValue(), clazz);
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
        if (values.containsKey(type)){
            try {
                Class clazz = values.get(type);
                Symbol c = (Symbol)clazz.newInstance();
                return c;
            } catch (Exception e) {
                throw new Exception();
            }
        } else throw new Exception();
    }
}

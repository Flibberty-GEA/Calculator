package com.sysgears.example.domain.symbols;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yevgen on 15.12.16.
 */
public abstract class Symbol implements Mom {
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

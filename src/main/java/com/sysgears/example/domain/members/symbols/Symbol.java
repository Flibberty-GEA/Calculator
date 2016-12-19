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
     * @throws InputExpressionException if something is wrong with the symbol
     */
    public static Symbol createInstance(Character value) throws Exception {
        Reflections reflections = new Reflections("");
        Set<Class<? extends Symbol>> subTypes = reflections.getSubTypesOf(Symbol.class);
        Map<String, Class> values = new HashMap<>();
        for (Class clazz:subTypes) {
            try {
                Symbol s = (Symbol)clazz.newInstance();
                values.put(s.getValue(), clazz);
            } catch (InstantiationException e) {
//                throw new InputExpressionException("");
            } catch (IllegalAccessException e) {
//                throw new InputExpressionException("");
            }
        }
        if (values.containsKey(String.valueOf(value))){
            try {
                Class clazz = values.get(String.valueOf(value));
                Symbol c = (Symbol)clazz.newInstance();
                return c;
            } catch (Exception e) {
                throw new InputExpressionException("");
            }
        } else throw new Exception();
    }
}

package com.sysgears.example.service;

import com.sysgears.example.members.Member;
import com.sysgears.example.members.Number;
import com.sysgears.example.members.Symbol;

import java.util.*;

/**
 * Utility for work with expression.
 *
 * @author Yevgen Goliuk
 */
public class ExpressionUtil {

    /**
     * It updates positions of Numbers in expression.
     *
     * @param oldExpression - expression, which may contain Numbers
     *                      with no correct Positions
     * @return an update expression with correct data
     */
    public static List<Member> updatePositions(final List<Member> oldExpression) {
        List<Member> resultExpression = oldExpression;
        for (int index = 0; index < resultExpression.size(); index++) {
            resultExpression.get(index).setPosition(index);
        }
        return resultExpression;
    }

    /**
     * Returns a list of expressions members for a fixed-size array.
     *
     * This method acts as a bridge between array-based and collection-based expressions.
     *
     * @param arrays the array by which the list will be backed
     * @return collection-based expressions
     * @throws InputException
     */
    public static List<Member> toListOfMembers(final String[] arrays) {
        List<Member> result = new LinkedList<>();
        for (int i = 0; i < arrays.length; i++) {
            try {
                result.add(new Number(arrays[i]));
            } catch (Exception e) {
                try {
                    result.add(Symbol.createInstance(arrays[i], i));
                } catch (Exception b) {
                    throw new InputException("\"" + arrays[i] + "\"");
                }
            }
        }
        return result;
    }

    /**
     * Check if list of expressions members is empty.
     *
     * @param list is collection-based expressions
     * @return true if list is empty
     */
    public static boolean isEmpty(final List<Member> list) {
        try {
            list.get(0);
            return false;
        } catch (IndexOutOfBoundsException i) {
            return true;
        }
    }
}

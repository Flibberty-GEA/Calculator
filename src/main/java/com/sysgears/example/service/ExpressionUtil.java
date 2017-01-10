package com.sysgears.example.service;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.domain.members.symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
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
        List<Member>  resultExpression = oldExpression;
        for (int index = 0; index < resultExpression.size(); index++) {
            resultExpression.get(index).setPosition(index);
        }
        return resultExpression;
    }


    /**
     * Returns a list of expressions members for a fixed-size array.
     * <p>
     * This method acts as a bridge between array-based and collection-based expressions.
     *
     * @param arrays the array by which the list will be backed
     * @return collection-based expressions
     * @throws InputException
     */
    public static List<Member> toListOfMembers(final String[] arrays) {
        List<Member> result = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            try {
                result.add(i, new Number(arrays[i]));
            } catch (Exception e) {
                try {
                    result.add(i, Symbol.createInstance(arrays[i], i));
                } catch (Exception b) {
                    throw new InputException("\"" + arrays[i] + "\"");
                }
            }
        }
        return result;
    }
}

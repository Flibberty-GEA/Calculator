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
        String[] str = toStringArrey(oldExpression);
        List<Member> resultExpression;
        resultExpression = toListOfMembers(str);
        return resultExpression;
    }

    /**
     * Returns a fixed-size array for a list of expression's members.
     * <p>
     * This method acts as a bridge between collection-based and array-based expressions.
     *
     * @param members the list by which the array will be backed
     * @return an array-based expressions
     */
    public static String[] toStringArrey(final List<Member> members) {
        String[] result = new String[members.size()];
        for (int j = 0; j < members.size(); j++) {
            result[j] = members.get(j).getValue();
        }
        return result;
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

    //    private void printMemberList(List<Member> members) {
//        try{
//            for (Member m : members) {
//                System.out.print(m.getValue());
//            }
//            System.out.println();
//        } catch (ConcurrentModificationException e){
//
//        }
//
//    }
//
//    private void printFunctionsList(List<Function> functions) {
//        try{
//        for (Function f : functions) {
//            System.out.print(f.getValue());
//        }
//        System.out.println();
//        } catch (ConcurrentModificationException e){
//
//        }
//    }
//
//    private void printStringArray(String[] arrays) {
//        for (int i = 0; i < arrays.length; i++) {
//            System.out.print(arrays[i]);
//        }
//        System.out.println();
//    }
}

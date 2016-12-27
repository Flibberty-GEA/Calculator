package com.sysgears.example.service;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.domain.members.symbols.ClosingBracket;
import com.sysgears.example.domain.members.symbols.functions.FuncktionComparatorByPriority;
import com.sysgears.example.domain.members.symbols.OpeningBracket;
import com.sysgears.example.domain.members.symbols.Symbol;
import com.sysgears.example.domain.members.symbols.functions.Function;
import com.sysgears.example.history.HistoryDAO;

import java.util.*;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

/**
 * Service class for calculating result of expression.
 *
 * @author Yevgen Goliuk
 */
public class MyExecutor {
    private HistoryDAO historyDAO;

    public MyExecutor(final HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }


    /**
     * Executes user's expression.
     *
     * @param userExpression input expression for calculating
     * @return result of input expression
    //     * @throws InputExpressionException if expression has invalid format
     */
    public Double execute(final String userExpression) {
        final String[] str = userExpression.split(" ");
        List<Member> expression2 = toListOfMembers(str);
        Double result = ((Number) brackets(expression2).get(0)).getDoubleValue();
        historyDAO.save(result.toString(), userExpression);
        result = isDivideByZero(result);
        return result;
    }

    /**
     * Parse brackets of the expression.
     *
     * @param expression input members of expression
     * @return result of input expression
//     * @throws InputExpressionException if expression has invalid format
     */
    private List<Member> brackets(final List<Member> expression) {
        List<Member> result = new ArrayList<>();
        List<Member> before = new ArrayList<>();
        for (int index = 0; index < expression.size(); index++) {
            Member member = expression.get(index);
            before = expression.subList(0, index + 1);
            if (member instanceof OpeningBracket) {
                List<Member> newExpression = expression.subList(index + 1, expression.size());
                before = expression.subList(0, index);
                result = brackets(newExpression);
                try {
                    before.addAll(result);
                } catch (Exception e) {

                }
                break;
            }
            if (member instanceof ClosingBracket) {
                List<Member> newExpression2 = expression.subList(0, index);
                result = calc(newExpression2);
                List<Member> after = expression.subList(index + 1, expression.size());
                result.addAll(after);
                return result;
            }
        }
        try {
            if (!before.isEmpty()) {
                result = before;
            }
        } catch (ConcurrentModificationException g) {

        }
        try {
            for (int k = 0; k < result.size(); k++) {
                Member m = result.get(k);
                if (m instanceof ClosingBracket) {
                    result = brackets(result);
                }
            }
            result = calc(result);
        } catch (ConcurrentModificationException e) {

        }
        return result;
    }

    /**
     * The basic method of calculating expressions without parentheses.
     *
     * @param inputExpression - an expression contains no opening brackets
     * @return result of the expression
//     * @throws InputExpressionException
//     * @throws InputCommandException
     */
    public List<Member> calc(final List<Member> inputExpression) {
        List<Member> resultExpression = updatePositions(inputExpression);
        List<Function> functions = new ArrayList<>();
        for (Member item : resultExpression) {
            if (item instanceof Function) functions.add((Function) item);
        }
        Collections.sort(functions, new FuncktionComparatorByPriority());
        resultExpression = calcOneOperation(resultExpression, functions);
        return resultExpression;
    }

    /**
     * It calculates one operation or function.
     *
     * It calculates the most priority function of expression.
     * If the expression contains more than one function, then it recursively calculates the remaining functions.
     *
     * @param expression is expression without brackets
     * @param functions list of sorted functions,
     *                  where the first function is more priority
     * @return result of input expression
//     * @throws InputExpressionException if expression has invalid format
     */
    public List<Member> calcOneOperation(final List<Member> expression, final List<Function> functions) {
        List<Member> resultExpression = new ArrayList<>(expression);
        if (functions.size() != 0) {
            resultExpression = functions.get(0).apply(resultExpression);
            if (resultExpression.get(resultExpression.size() - 1) instanceof ClosingBracket) {
                resultExpression.remove(resultExpression.size() - 1);
            }
            if (functions.size() > 1) {
                resultExpression = calc(resultExpression);
            }
        }
        return resultExpression;
    }

    /**
     * It updates positions of Numbers in expression.
     *
     * @param oldExpression - expression, which may contain Numbers
     *                      with no correct Positions
     * @return an update expression with correct data
    //     * @throws InputExpressionException
    //     * @throws InputCommandException
     */
    private List<Member> updatePositions(final List<Member> oldExpression){
        String[] str = toStringArrey(oldExpression);
        List<Member> resultExpression;
        resultExpression = toListOfMembers(str);
        return resultExpression;
    }

    /**
     * Returns a fixed-size array for a list of expression's members.
     *
     * This method acts as a bridge between collection-based and array-based expressions.
     *
     * @param members the list by which the array will be backed
     * @return an array-based expressions
     */
    private String[] toStringArrey(final List<Member> members) {
        String[] result = new String[members.size()];
        for (int j = 0; j < members.size(); j++) {
            result[j] = members.get(j).getValue();
        }
        return result;
    }

    /**
     * Returns a list of expressions members for a fixed-size array.
     *
     * This method acts as a bridge between array-based and collection-based expressions.
     *
     * @param arrays the array by which the list will be backed
     * @return collection-based expressions
     */
    private List<Member> toListOfMembers(final String[] arrays) {
        List<Member> result = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            try {
                result.add(i, new Number(arrays[i]));
            } catch (Exception e) {
                try {
                    result.add(i, Symbol.createInstance(arrays[i], i));
                } catch (Exception b) {
                }
            }
        }
        return result;
    }

    /**
     * Checks whether the division by zero.
     *
     * @param result of calculation
     * @return double result
     * @throws ArithmeticException thrown when expression include "divide by zero"
     */
    private Double isDivideByZero(final Double result) {
        if (result.equals(Infinity)||result.equals(NaN)) {
            throw new ArithmeticException("Don't divide by zero.");
        } else return result;
    }
//    private void printMemberList(List<Member> members) {
//        for (Member m : members) {
//            System.out.print(m.getValue());
//        }
//        System.out.println();
//    }
//
//    private void printFunctionsList(List<Function> functions) {
//        for (Function f : functions) {
//            System.out.print(f.getValue());
//        }
//        System.out.println();
//    }

//    private void printStringArray(String[] arrays) {
//        for (int i = 0; i < arrays.length; i++) {
//            System.out.print(arrays[i]);
//        }
//        System.out.println();
//    }
}

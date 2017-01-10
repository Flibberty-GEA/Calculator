package com.sysgears.example.service;

import com.sysgears.example.members.Member;
import com.sysgears.example.members.delimiters.ClosingBracket;
import com.sysgears.example.members.functions.FuncktionComparatorByPriority;
import com.sysgears.example.members.functions.Function;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Service for calculating result of expression.
 *
 * @author Yevgen Goliuk
 */
public class Calculator {
    /**
     * The basic method of calculating expressions without parentheses.
     *
     * @param inputExpression - an expression contains no opening brackets
     * @return result of the expression
     */
    public List<Member> calc(final List<Member> inputExpression) {
        List<Member> resultExpression = ExpressionUtil.updatePositions(inputExpression);
        List<Function> functions = new LinkedList<>();
        for (Member member : resultExpression) {
            if (member instanceof Function) functions.add((Function) member);
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
     * @param functions  list of sorted functions,
     *                   where the first function is more priority
     * @return result of input expression
     */
    public List<Member> calcOneOperation(final List<Member> expression, final List<Function> functions) {
        List<Member> resultExpression = new LinkedList<>(expression);
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
}

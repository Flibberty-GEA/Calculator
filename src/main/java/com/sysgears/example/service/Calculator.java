package com.sysgears.example.service;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.symbols.ClosingBracket;
import com.sysgears.example.domain.members.symbols.functions.FuncktionComparatorByPriority;
import com.sysgears.example.domain.members.symbols.functions.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
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
     * <p>
     * It calculates the most priority function of expression.
     * If the expression contains more than one function, then it recursively calculates the remaining functions.
     *
     * @param expression is expression without brackets
     * @param functions  list of sorted functions,
     *                   where the first function is more priority
     * @return result of input expression
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
}

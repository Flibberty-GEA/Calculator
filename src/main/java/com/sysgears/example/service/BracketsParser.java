package com.sysgears.example.service;

import com.sysgears.example.members.Member;
import com.sysgears.example.members.delimiters.ClosingBracket;
import com.sysgears.example.members.delimiters.OpeningBracket;
import com.sysgears.example.members.functions.Function;

import java.util.*;

/**
 * Service for parse brackets of an expression.
 *
 * @author Yevgen Goliuk
 */
public class BracketsParser {

    /**
     * Parse brackets of the expression.
     *
     * @param expression input members of expression
     * @return result of input expression
     */
    public List<Member> brackets(final List<Member> expression) {
        Calculator calculator = new Calculator();
        List<Member> result = new ArrayList<>();
        List<Member> beforeResult = new ArrayList<>();

        for (int index = 0; index < expression.size(); index++) {
            Member member = expression.get(index);
            beforeResult = expression.subList(0, index + 1);
            List<Member> beforeReserve = new ArrayList<>(beforeResult);
            if (member instanceof OpeningBracket) {
                List<Member> newExpression = expression.subList(index + 1, expression.size());
                beforeResult = expression.subList(0, index);
                result = brackets(newExpression);
                try {
                    beforeResult.addAll(result);
                } catch (Exception e) {
                    if (beforeReserve.get(0) instanceof Function) {
                        beforeResult = beforeReserve.subList(0, 1);
                        beforeResult.addAll(result);
                    }
                }
                break;
            }
            if (member instanceof ClosingBracket) {
                List<Member> newExpression2 = expression.subList(0, index);
                result = calculator.calc(newExpression2);
                List<Member> afterResult = expression.subList(index + 1, expression.size());
                result.addAll(afterResult);
                return result;
            }
        }

        if (!(ExpressionUtil.isEmpty(beforeResult))) {
            result = beforeResult;
        }

        for (int index = 0; index < result.size(); index++) {
            Member member = result.get(index);
            if (member instanceof ClosingBracket) {
                result = brackets(result);
            }
        }
        result = calculator.calc(result);

        return result;
    }
}

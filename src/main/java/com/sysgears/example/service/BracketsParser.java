package com.sysgears.example.service;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.symbols.ClosingBracket;
import com.sysgears.example.domain.members.symbols.OpeningBracket;
import com.sysgears.example.domain.members.symbols.functions.Function;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
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
        List<Member> before = new ArrayList<>();
        for (int index = 0; index < expression.size(); index++) {
            Member member = expression.get(index);
            before = expression.subList(0, index + 1);
            List<Member> before2 = new ArrayList<>(before);
            if (member instanceof OpeningBracket) {
                List<Member> newExpression = expression.subList(index + 1, expression.size());
                before = expression.subList(0, index);
                result = brackets(newExpression);
                try {
                    before.addAll(result);
                } catch (Exception e) {
                    if (before2.get(0) instanceof Function) {
                        before = before2.subList(0, 1);
                        before.addAll(result);
                    }
                }
                break;
            }
            if (member instanceof ClosingBracket) {
                List<Member> newExpression2 = expression.subList(0, index);
                result = calculator.calc(newExpression2);
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
            //  to do nothing
        }
        try {
            for (int k = 0; k < result.size(); k++) {
                Member m = result.get(k);
                if (m instanceof ClosingBracket) {
                    result = brackets(result);
                }
            }
            result = calculator.calc(result);
        } catch (ConcurrentModificationException e) {
            //  to do nothing
        }
        return result;
    }
}

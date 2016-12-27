package com.sysgears.example.service;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.domain.members.symbols.ClosingBracket;
import com.sysgears.example.domain.members.symbols.OpeningBracket;
import com.sysgears.example.domain.members.symbols.Symbol;
import com.sysgears.example.domain.members.symbols.operators.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

/**
 * @author Yevgen Goliuk
 */
public class MyExecutor {
    private HistoryDAO historyDAO;

    public MyExecutor(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    public Double execute(String userExpression) {
        final String[] str = userExpression.split(" ");
        List<Member> expression2 = toListOfMembers(str);
        Double result = ((Number) brackets(expression2).get(0)).getDoubleValue();
        historyDAO.save(result.toString(), userExpression);
        result = isDivideByZero(result);
        return result;
    }

    /**
     * @param expression input members of expression
     * @return result of input expression
//     * @throws InputExpressionException if expression has invalid format
     */
    private List<Member> brackets(List<Member> expression) {
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
                String[] strr = toStringArrey(newExpression2);
                result = parse(strr);
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
            result = parse(toStringArrey(result));
        } catch (ConcurrentModificationException e) {

        }
        return result;
    }

    public List<Member> parse(String[] str) {
        List<Member> expression;
        expression = toListOfMembers(str);
        List<Function> functions = new ArrayList<>();
        for (Member item : expression) {
            if (item instanceof Function) functions.add((Function) item);
        }
        Collections.sort(functions, new FuncktionByPriority());
        expression = calc(expression, functions);
        return expression;
    }

    public List<Member> calc(List<Member> expression, List<Function> functions) {
        if (functions.size() != 0) {
            expression = functions.get(0).apply(expression);
            if (expression.get(expression.size() - 1) instanceof ClosingBracket) {
                expression.remove(expression.size() - 1);
            }
            String[] str = new String[expression.size()];

            for (int i = 0; i < expression.size(); i++) {
                str[i] = expression.get(i).getValue();
            }
            if (functions.size() > 1) {
                expression = parse(str);
            }
        }

        return expression;
    }

    private void printStringArray(String[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i]);
        }
        System.out.println();
    }

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

    private String[] toStringArrey(List<Member> members) {
        String[] result = new String[members.size()];
        for (int j = 0; j < members.size(); j++) {
            result[j] = members.get(j).getValue();
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
}

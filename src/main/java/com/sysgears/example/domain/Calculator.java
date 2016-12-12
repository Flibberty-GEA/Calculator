package com.sysgears.example.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 *
 */
public class Calculator {
    /**
     * Calculates the expression written in Reverse Polish Notation
     * @param inputExpression
     * @return double result
     */
    public static double calculate(String inputExpression) throws Exception {
        double dA = 0, dB = 0;
        String sTmp;
        Deque<Double> stack = new ArrayDeque<Double>();
        StringTokenizer st = new StringTokenizer(inputExpression);
        while(st.hasMoreTokens()) {
            try {
                sTmp = st.nextToken().trim();
                if (1 == sTmp.length() && isOperator(sTmp.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Неверное количество данных в стеке для операции " + sTmp);
                    }
                    dB = stack.pop();
                    dA = stack.pop();
                    switch (sTmp.charAt(0)) {
                        case '+':
                            dA += dB;
                            break;
                        case '-':
                            dA -= dB;
                            break;
                        case '/':
                            dA /= dB;
                            break;
                        case '*':
                            dA *= dB;
                            break;
                        case '%':
                            dA %= dB;
                            break;
                        case '^':
                            dA = Math.pow(dA, dB);
                            break;
                        default:
                            throw new Exception("Недопустимая операция " + sTmp);
                    }
                    stack.push(dA);
                } else {
                    dA = Double.parseDouble(sTmp);
                    stack.push(dA);
                }
            } catch (Exception e) {
                throw new Exception("Недопустимый символ в выражении");
            }
        }

        if (stack.size() > 1) {
            throw new Exception("Количество операторов не соответствует количеству операндов");
        }

        return stack.pop();
    }

    /**
     * Convert user's expression in Reverse Polish notation (RPN).
     * Reverse Polish notation (RPN) is a mathematical notation
     * in which every operator follows all of its operands.
     * It is also known as postfix notation and does not need any parentheses
     * as long as each operator has a fixed number of operands.
     * @param userExpression - request user for expression
     * @return resultExpression - output string in RPN
     */
    public static String toRPN(String userExpression) throws Exception {
        StringBuilder sbStack = new StringBuilder(""), resultExpression = new StringBuilder("");
        char cIn, cTmp;

        for (int i = 0; i < userExpression.length(); i++) {
            cIn = userExpression.charAt(i);
            if (isOperator(cIn)) {
                while (sbStack.length() > 0) {
                    cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                    if (isOperator(cTmp) && (priorityOfOperator(cIn) <= priorityOfOperator(cTmp))) {
                        resultExpression.append(" ").append(cTmp).append(" ");
                        sbStack.setLength(sbStack.length()-1);
                    } else {
                        resultExpression.append(" ");
                        break;
                    }
                }
                resultExpression.append(" ");
                sbStack.append(cIn);
            } else if ('(' == cIn) {
                sbStack.append(cIn);
            } else if (')' == cIn) {
                cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                while ('(' != cTmp) {
                    if (sbStack.length() < 1) {
                        throw new Exception("Ошибка разбора скобок. Проверьте правильность выражения.");
                    }
                    resultExpression.append(" ").append(cTmp);
                    sbStack.setLength(sbStack.length()-1);
                    cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                }
                sbStack.setLength(sbStack.length()-1);
            } else {
                // Если символ не оператор - добавляем в выходную последовательность
                resultExpression.append(cIn);
            }
        }

        // Если в стеке остались операторы, добавляем их в входную строку
        while (sbStack.length() > 0) {
            resultExpression.append(" ").append(sbStack.substring(sbStack.length()-1));
            sbStack.setLength(sbStack.length()-1);
        }

        return  resultExpression.toString();
    }

    /**
     * Checking whether the current symbol the operator
     */
    private static boolean isOperator(char c) {
        switch (c) {
            case '-':
            case '+':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }

    /**
     * Checking operator priority
     * @param operator char
     * @return byte
     */
    private static byte priorityOfOperator(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
            case '%':
                return 2;
        }
        return 1; // Here is the + and -
    }

}

package com.sysgears.example.domain;

import com.sysgears.example.exceptions.InputArgumentsException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

/**
 * Calculates the expression
 */
public class Calculator {
    /**
     * Calculates the expression written in Reverse Polish Notation
     * @param inputExpression
     * @return double result
     */
    public double calculate(String inputExpression) throws Exception {
        double operand = 0, secondOperand = 0;
        String symbol;
        Deque<Double> stack = new ArrayDeque<Double>();

        String expressionRPN = toRPN(inputExpression);
        StringTokenizer tokenizer = new StringTokenizer(expressionRPN);
        while(tokenizer.hasMoreTokens()) {
            symbol = tokenizer.nextToken().trim();
            try {
                if (1 == symbol.length() && isOperator(symbol.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new InputArgumentsException("Неверное количество данных в стеке для операции.");
                    }
                    secondOperand = stack.pop();
                    operand = stack.pop();
                    switch (symbol.charAt(0)) {
                        case '+':
                            operand += secondOperand;
                            break;
                        case '-':
                            operand -= secondOperand;
                            break;
                        case '/':
                            operand /= secondOperand;
                            break;
                        case '*':
                            operand *= secondOperand;
                            break;
/*                        case '%':
                            operand %= secondOperand;
                            break;*/
                        case '^':
                            operand = Math.pow(operand, secondOperand);
                            break;
                        default:
                            throw new InputArgumentsException("Недопустимая операция " + symbol);
                    }
                    stack.push(operand);
                } else {
                    operand = Double.parseDouble(symbol);
                    stack.push(operand);
                }
            }  catch (InputArgumentsException e) {
                throw e;
            } catch (Exception e) {
//                e.printStackTrace();
                throw new InputArgumentsException("Недопустимый символ в выражении -> "+symbol);
            }
        }

        if (stack.size() > 1) {
            throw new InputArgumentsException("Количество операторов не соответствует количеству операндов");
        }

        Double result = stack.pop();

        if (result.equals(Infinity)||result.equals(NaN)) throw new InputArgumentsException("На 0 не делится");
        else return result;
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
    private String toRPN(String userExpression) throws Exception {
        StringBuilder sbStack = new StringBuilder(""), resultExpression = new StringBuilder("");
        char cIn, cTmp;

        if (userExpression.contains("(-")) userExpression=userExpression.replace("(-", "(0-");

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
                        throw new InputArgumentsException("Ошибка разбора скобок. Проверьте правильность выражения.");
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
    private boolean isOperator(char c) {
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
    private byte priorityOfOperator(char operator) {
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

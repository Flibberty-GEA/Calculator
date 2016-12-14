package com.sysgears.example.service;

import com.sysgears.example.domain.ResultsHistory;
import com.sysgears.example.exceptions.InputCommandException;
import com.sysgears.example.exceptions.InputExpressionException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

/**
 * Calculates the expression
 */
public class Calculator {

    private ResultsHistory resultsHistory = new ResultsHistory();

    public String getAllRecords(){
        return resultsHistory.getAllRecords().toString();
    }
    public String getUniqueRecords(){
        return resultsHistory.getUniqueRecords().toString();
    }


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
                        throw new InputExpressionException("Неверное количество данных в стеке для операции.\nEach operator must has a fixed number of operands.");
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
                        case '^':
                            operand = Math.pow(operand, secondOperand);
                            break;
                        default:
                            throw new InputExpressionException("Недопустимая операция " + symbol);
                    }
                    stack.push(operand);
                } else if (1 == symbol.length() && symbol.charAt(0)=='(') {
                    throw new InputExpressionException("Ошибка разбора скобок. Проверьте правильность выражения."+inputExpression);
                } else {
                    operand = Double.parseDouble(symbol);
                    stack.push(operand);
                }
            }  catch (InputExpressionException e) {
                throw e;
            } catch (Exception e) {
                if (!inputExpression.contains("exit")) throw new InputExpressionException("Недопустимый символ в выражении -> "+inputExpression+". You can use operators as +, 1, *, /, ^.");
                else throw new InputCommandException("Введите exit без других символов");
            }
        }

        if (stack.size() > 1) {
            throw new InputExpressionException("Количество операторов не соответствует количеству операндов");
        }

        Double result = stack.pop();

        result = checkForInfinityAndNaN(result, inputExpression);

        resultsHistory.saveResult(result.toString());
        return result;

//        if ((result.equals(Infinity)&&inputExpression.contains("^(-"))&&!inputExpression.contains("^(-0")) throw new ArithmeticException("0^(-n) = 1/0^n. Если n!=0 то после возведения 0 в степень n в знаменателе окажется 0, а на 0 не делится.");
//        else if (result.equals(Infinity)||result.equals(NaN)) throw new ArithmeticException("На 0 не делится.");
//        else return result;
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
        StringBuilder serviceOperatorsStack = new StringBuilder(""), resultExpression = new StringBuilder("");
        char currentSymbol, serviceSymbol;
        if ((userExpression.trim().charAt(0)=='-') || (userExpression.trim().charAt(0)=='+')) userExpression="0"+userExpression;
        if (userExpression.contains("(-")) userExpression=userExpression.replace("(-", "(0-");

        for (int i = 0; i < userExpression.length(); i++) {
            currentSymbol = userExpression.charAt(i);
            if (isOperator(currentSymbol)) {
                while (serviceOperatorsStack.length() > 0) {
                    serviceSymbol = serviceOperatorsStack.substring(serviceOperatorsStack.length()-1).charAt(0);
                    if (isOperator(serviceSymbol) && (priorityOfOperator(currentSymbol) <= priorityOfOperator(serviceSymbol))) {
                        resultExpression.append(" ").append(serviceSymbol).append(" ");
                        serviceOperatorsStack.setLength(serviceOperatorsStack.length()-1);
                    } else {
                        resultExpression.append(" ");
                        break;
                    }
                }
                resultExpression.append(" ");
                serviceOperatorsStack.append(currentSymbol);
            } else if ('(' == currentSymbol) {
                serviceOperatorsStack.append(currentSymbol);
            } else if (')' == currentSymbol) {
                try {
                    serviceSymbol = serviceOperatorsStack.substring(serviceOperatorsStack.length()-1).charAt(0);
                    while ('(' != serviceSymbol) {
                        resultExpression.append(" ").append(serviceSymbol);
                        serviceOperatorsStack.setLength(serviceOperatorsStack.length()-1);
                        serviceSymbol = serviceOperatorsStack.substring(serviceOperatorsStack.length()-1).charAt(0);
                    }
                    serviceOperatorsStack.setLength(serviceOperatorsStack.length()-1);

                } catch (Exception e){
                    throw new InputExpressionException("Ошибка разбора скобок. Проверьте правильность выражения."+userExpression);
                }
            } else {
                // Если символ не оператор - добавляем в выходную последовательность
                resultExpression.append(currentSymbol);
            }
        }
        // Если в стеке остались операторы, добавляем их в входную строку
        while (serviceOperatorsStack.length() > 0) {
            resultExpression.append(" ").append(serviceOperatorsStack.substring(serviceOperatorsStack.length()-1));
            serviceOperatorsStack.setLength(serviceOperatorsStack.length()-1);
        }
        return  resultExpression.toString();
    }

    /**
     * Checking whether the current symbol the operator
     */
    private boolean isOperator(char symbol) {
        switch (symbol) {
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

    private Double checkForInfinityAndNaN(Double result, String inputExpression) throws ArithmeticException {
        if ((result.equals(Infinity)&&inputExpression.contains("^(-"))&&!inputExpression.contains("^(-0")) throw new ArithmeticException("0^(-n) = 1/0^n. Если n!=0 то после возведения 0 в степень n в знаменателе окажется 0, а на 0 не делится.");
        else if (result.equals(Infinity)||result.equals(NaN)) throw new ArithmeticException("На 0 не делится.");
        else return result;
    }
}

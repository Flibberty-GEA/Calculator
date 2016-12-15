package com.sysgears.example.service;

import com.sysgears.example.domain.symbols.Symbol;
import com.sysgears.example.domain.symbols.SymbolFactory;
import com.sysgears.example.domain.symbols.operators.Operator;
import com.sysgears.example.exceptions.InputExpressionException;


import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Created by yevgen on 15.12.16.
 */
public class ParserRPN {

    SymbolFactory factory = new SymbolFactory();

    /**
     * Convert user's expression in Reverse Polish notation (RPN).
     * Reverse Polish notation (RPN) is a mathematical notation
     * in which every operator follows all of its operands.
     * It is also known as postfix notation and does not need any parentheses
     * as long as each operator has a fixed number of operands.
     * @param userExpression - request user for expression
     * @return resultExpression - output string in RPN
     */
    public String toRPN(String userExpression) throws Exception {

        Deque<Symbol> serviceSymbolStack = new ArrayDeque<>();
        StringBuilder resultExpression = new StringBuilder("");
        Symbol serviceSymbol;
        char currentSymbol;
        if ((userExpression.trim().charAt(0)=='-') || (userExpression.trim().charAt(0)=='+')) userExpression="0"+userExpression;
        if (userExpression.contains("(-")) userExpression=userExpression.replace("(-", "(0-");
//        if (userExpression.contains("--")) userExpression=userExpression.replace("--", "+");
//        else if (userExpression.contains("+-")) userExpression=userExpression.replace("+-", "-");
//        else if (userExpression.contains("*-")) userExpression=userExpression.replace("*-", "*1+0-");
//        else if (userExpression.contains("/-")) userExpression=userExpression.replace("/-", "/1+0-");

        for (int i = 0; i < userExpression.length(); i++) {
            currentSymbol = userExpression.charAt(i);

            if ('(' == currentSymbol){
                serviceSymbolStack.push(factory.createInstance(currentSymbol));
            }else if (')' == currentSymbol) {
                try {
                    serviceSymbol = serviceSymbolStack.peek();
                    while ('(' != serviceSymbol.getValue()) {
                        resultExpression.append(" ").append(serviceSymbol.getValue());
                        serviceSymbolStack.pop();
                        serviceSymbol = serviceSymbolStack.peek();
                    }
                    serviceSymbolStack.pop();

                } catch (Exception e){
                    throw new InputExpressionException("Ошибка разбора скобок. Проверьте правильность выражения."+userExpression);
                }
            } else if (true) {
//                else if (Double.valueOf(String.valueOf(currentSymbol)) instanceof Double)
                try{
                    Double.valueOf(String.valueOf(currentSymbol));
                    resultExpression.append(currentSymbol);
                }catch (NumberFormatException e){
                    while (serviceSymbolStack.size() > 0) {
                        serviceSymbol = serviceSymbolStack.peek();
                        if (serviceSymbol instanceof Operator && (factory.createInstance(currentSymbol).getPriority() <= serviceSymbol.getPriority())) {
                            resultExpression.append(" ").append(serviceSymbol.getValue()).append(" ");
                            serviceSymbolStack.pop();
                        } else {
                            resultExpression.append(" ");
//                            resultExpression.append(currentSymbol);
                            break;
                        }
                    }
                    resultExpression.append(" ");
                    serviceSymbolStack.push(factory.createInstance(currentSymbol));
                }
            } else {
            }
        }

        // Если в стеке остались операторы, добавляем их в входную строку
        while (serviceSymbolStack.size() > 0) {
            resultExpression.append(" ").append(serviceSymbolStack.pop().getValue());
        }
        return  resultExpression.toString();
    }
}

package com.sysgears.example.service;

import com.sysgears.example.domain.members.*;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.domain.members.symbols.ClosingBracket;
import com.sysgears.example.domain.members.symbols.OpeningBracket;
import com.sysgears.example.domain.members.symbols.Symbol;
import com.sysgears.example.exceptions.InputExpressionException;


import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Convert user's expression in Reverse Polish notation (RPN).
 *
 * @author  Yevgen Goliuk
 */
public class ParserRPN {

    /**
     * Convert user's expression in Reverse Polish notation (RPN).
     *
     * Reverse Polish notation (RPN) is a mathematical notation.
     * in which every operator follows all of its operands
     * It is also known as postfix notation and does not need any parentheses
     * as long as each operator has a fixed number of operands.
     *
     * @param userExpression - request user for expression
     * @return a Deque of Members in the ordered expression of RPN
     * @throws InputExpressionException
     */
    public Deque<Member> toRPN(final String userExpression) throws Exception {

        String inputExpression = new String(userExpression);
        Deque<Symbol> serviceSymbolStack = new ArrayDeque<>();
        Deque<Member> result = new ArrayDeque<>();
        StringBuilder number = new StringBuilder("");
        Symbol serviceSymbol;
        Symbol currentSymbol;
        char currentChar;

        inputExpression = prepareNegativeNumbers(inputExpression);

        for (int i = 0; i < inputExpression.length(); i++) {
            currentChar = inputExpression.charAt(i);
            try{
                currentSymbol = Symbol.createInstance(currentChar);
                try{
                    result.addLast(new Number(number.toString()));
                    number = new StringBuilder("");
                }catch (Exception e){

                }
                if (currentSymbol.isOperator()) {
                    while (serviceSymbolStack.size() > 0) {
                        serviceSymbol = serviceSymbolStack.peekLast();
                        if (serviceSymbol.isOperator() && (currentSymbol.getPriority() <= serviceSymbol.getPriority())) {
                            result.addLast(serviceSymbol);
                            serviceSymbolStack.removeLast();
                        } else {
                            break;
                        }
                    }
                    serviceSymbolStack.addLast(currentSymbol);
                } else if (currentSymbol instanceof OpeningBracket) {
                    serviceSymbolStack.addLast(currentSymbol);
                } else if (currentSymbol instanceof ClosingBracket) {
                    try {
                        serviceSymbol = serviceSymbolStack.pollLast();
                        while (!(serviceSymbol instanceof OpeningBracket)) {
                            result.addLast(serviceSymbol);
                            serviceSymbol = serviceSymbolStack.removeLast();
                        }
                    } catch (Exception e){
                        throw new InputExpressionException("Error parsing brackets. Check the expression -> "+inputExpression);
                    }
                }
            }catch (Exception e){
                number.append(currentChar);
            }
        }
        if (number.length() > 0) {
            result.addLast(new Number(number.toString()));
        }
        while (serviceSymbolStack.size() > 0) {
            result.addLast(serviceSymbolStack.removeLast());
        }

        return  result;
    }

    public String prepareNegativeNumbers(final String inputExpression){
        String result = new String(inputExpression);
        if ((result.trim().charAt(0)=='-') || (result.trim().charAt(0)=='+')) result="0"+result;
        if (result.contains("(-")) result=result.replace("(-", "(0-");
        return  result;
    }
}
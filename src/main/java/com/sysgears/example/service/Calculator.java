package com.sysgears.example.service;

import com.sysgears.example.domain.symbols.Mom;
import com.sysgears.example.domain.symbols.Number;
import com.sysgears.example.domain.symbols.OpeningBracket;
import com.sysgears.example.domain.symbols.operators.Operator;
import com.sysgears.example.exceptions.InputCommandException;
import com.sysgears.example.exceptions.InputExpressionException;

import java.util.ArrayDeque;
import java.util.Deque;


import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

/**
 * Class for calculating result of expression
 */
public class Calculator {

    private HistoryDAO historyDAO;
    ParserRPN parser = new ParserRPN();

    public Calculator(final HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    /**
     * Calculates the expression written in Reverse Polish Notation
     * @param inputExpression
     * @return double result
     */
    public double calculate(final String inputExpression) throws Exception {
        double operand = 0, secondOperand = 0;
        Mom symbol;
        Deque<Double> stack = new ArrayDeque<Double>();

        Deque<Mom> expressionRPN = parser.toRPN(inputExpression);

        while(expressionRPN.size() != 0) {
            symbol = expressionRPN.pop();
            try {
                if (symbol instanceof Operator) {
                    if (stack.size() < 2) {
                        throw new InputExpressionException("Неверное количество данных в стеке для операции.\nEach operator must has a fixed number of operands.");
                    }
                    secondOperand = stack.pop();
                    operand = stack.pop();
                    operand = ((Operator) symbol).apply(operand, secondOperand);
                    stack.push(operand);
                } else if (symbol instanceof OpeningBracket) {
                    throw new InputExpressionException("Ошибка разбора скобок. Проверьте правильность выражения."+inputExpression);
                } else {
                    Number number = new Number(symbol.getValue());
                    operand = number.getDoubleValue();
                    stack.push(operand);
                }
            }  catch (InputExpressionException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                if (!inputExpression.contains("exit")) throw new InputExpressionException("Недопустимый символ в выражении -> "+inputExpression+". You can use operators as +, 1, *, /, ^.");
                else throw new InputCommandException("Введите exit без других символов");
            }
        }

        if (stack.size() > 1) {
            throw new InputExpressionException("Количество операторов не соответствует количеству операндов");
        }

        Double result = stack.pop();

        result = checkForInfinityAndNaN(result, inputExpression);

        historyDAO.save(result.toString());
        return result;
    }

    private Double checkForInfinityAndNaN(Double result, String inputExpression) throws ArithmeticException {
        if ((result.equals(Infinity)&&inputExpression.contains("^(-"))&&!inputExpression.contains("^(-0")) throw new ArithmeticException("0^(-n) = 1/0^n. Если n!=0 то после возведения 0 в степень n в знаменателе окажется 0, а на 0 не делится.");
        else if (result.equals(Infinity)||result.equals(NaN)) throw new ArithmeticException("На 0 не делится.");
        else return result;
    }
}

package com.sysgears.example;

import com.sysgears.example.domain.symbols.Symbol;
import com.sysgears.example.exceptions.InputCommandException;
import com.sysgears.example.exceptions.InputExpressionException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

/**
 * Class for calculating result of expression
 */
public class CalculatorCopy {




//
//
//
//
//    private HistoryDAO historyDAO;
//    ParserRPN parser = new ParserRPN();
//    SymbolFactory factory = new SymbolFactory();
//
//    public Calculator(final HistoryDAO historyDAO) {
//        this.historyDAO = historyDAO;
//    }
//
//    /**
//     * Calculates the expression written in Reverse Polish Notation
//     * @param inputExpression
//     * @return double result
//     */
//    public double calculate(final String inputExpression) throws Exception {
//        double operand = 0, secondOperand = 0;
//        String symbol;
//        Deque<Double> stack = new ArrayDeque<Double>();
//
//        String expressionRPN = parser.toRPN(inputExpression);
//
//        StringTokenizer tokenizer = new StringTokenizer(expressionRPN);
//        while(tokenizer.hasMoreTokens()) {
//            symbol = tokenizer.nextToken().trim();
//            try {
//                if (1 == symbol.length()){
//
//                    if (symbol.charAt(0)=='(') {
//                        throw new InputExpressionException("Ошибка разбора скобок. Проверьте правильность выражения."+inputExpression);
//                    } else {
//                        try{
//                            Symbol symbol122 = factory.createInstance(symbol.charAt(0));
//                            if (symbol122.isOperator()) {
//                                if (stack.size() < 2) {
//                                    throw new InputExpressionException("Неверное количество данных в стеке для операции.\nEach operator must has a fixed number of operands.");
//                                }
//                                secondOperand = stack.pop();
//                                operand = stack.pop();
//                                switch (symbol.charAt(0)) {
//                                    case '+':
//                                        operand += secondOperand;
//                                        break;
//                                    case '-':
//                                        operand -= secondOperand;
//                                        break;
//                                    case '/':
//                                        operand /= secondOperand;
//                                        break;
//                                    case '*':
//                                        operand *= secondOperand;
//                                        break;
//                                    case '^':
//                                        operand = Math.pow(operand, secondOperand);
//                                        break;
//                                    default:
//                                        throw new InputExpressionException("Недопустимая операция " + symbol);
//                                }
//                                stack.push(operand);
//                            }
//                        }catch (Exception e){
//                        }
//                    }
//                } else {
//                    operand = Double.parseDouble(symbol);
//                    stack.push(operand);
//                }
//            }  catch (InputExpressionException e) {
//                throw e;
//            } catch (Exception e) {
//                if (!inputExpression.contains("exit")) throw new InputExpressionException("Недопустимый символ в выражении -> "+inputExpression+". You can use operators as +, 1, *, /, ^.");
//                else throw new InputCommandException("Введите exit без других символов");
//            }
//        }
//
//        if (stack.size() > 1) {
//            throw new InputExpressionException("Количество операторов не соответствует количеству операндов");
//        }
//
//        System.out.println(stack.toString());
//        Double result = stack.pop();
//
//        result = checkForInfinityAndNaN(result, inputExpression);
//
//        historyDAO.save(result.toString());
//        return result;
//    }
//
//    /**
//     * Checking whether the current symbol the operator
//     */
//    private boolean isOperator(char symbol) {
//        switch (symbol) {
//            case '-':
//            case '+':
//            case '*':
//            case '/':
//            case '^':
//                return true;
//        }
//        return false;
//    }
//
//    private Double checkForInfinityAndNaN(Double result, String inputExpression) throws ArithmeticException {
//        if ((result.equals(Infinity)&&inputExpression.contains("^(-"))&&!inputExpression.contains("^(-0")) throw new ArithmeticException("0^(-n) = 1/0^n. Если n!=0 то после возведения 0 в степень n в знаменателе окажется 0, а на 0 не делится.");
//        else if (result.equals(Infinity)||result.equals(NaN)) throw new ArithmeticException("На 0 не делится.");
//        else return result;
//    }



}
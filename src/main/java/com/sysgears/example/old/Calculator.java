package com.sysgears.example.old;

import com.sysgears.example.service.InputCommandException;
import com.sysgears.example.service.InputExpressionException;
import com.sysgears.example.history.HistoryDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Service class to calculate result of expression
 *
 * @author  Yevgen Goliuk
 */
public class Calculator {
    public static final Logger log = LogManager.getLogger(Calculator.class);

    private HistoryDAO historyDAO;
//    ParserRPN parser = new ParserRPN();

    public Calculator(final HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    /**
     * Calculates the expression written in Reverse Polish Notation
     *
     * @param inputExpression - the expression entered by the user
     * @return double result
     * @throws InputExpressionException
     * @throws InputCommandException
     */
//    public double calculate(final String inputExpression) throws Exception {
//
//        double operand = 0, secondOperand = 0;
//        Member symbol;
//        Deque<Double> stack = new ArrayDeque<Double>();
////        Deque<Member> expressionRPN = parser.toRPN(inputExpression);
//
//        while(expressionRPN.size() != 0) {
//            symbol = expressionRPN.pop();
//            try {
//                if (symbol instanceof Operation) {
//                    if (stack.size() < 2) {
//                        throw new InputExpressionException("The number of operations does not correspond " +
//                                                            "to the number of operands." +
//                                                            "\nEach operator must has a fixed number of operands.");
//                    }
//                    secondOperand = stack.pop();
//                    operand = stack.pop();
//                    log.info("Start operation '" + symbol.getClass().getSimpleName() + ".class' method apply(Double... operands) with operands => " + operand + ", " + secondOperand);
//                    operand = ((Operation) symbol).apply(operand, secondOperand);
//                    stack.push(operand);
//                } else if (symbol instanceof OpeningBracket) {
//                    throw new InputExpressionException("Error parsing brackets. Check the expression -> "+inputExpression);
//                } else {
//                    Number number = new Number(symbol.getValue());
//                    operand = number.getDoubleValue();
//                    stack.push(operand);
//                }
//            }  catch (InputExpressionException e) {
//                throw e;
//            } catch (Exception e) {
//                if (!inputExpression.contains("exit")) {
//                    throw new InputExpressionException("Invalid character in an expression -> "
//                            + inputExpression + "\nYou can use operators as +, 1, *, /, ^.");
//                }
//                else throw new InputCommandException("Type exit, with no other characters");
//            }
//        }
//        if (stack.size() > 1) {
//            throw new InputExpressionException("The number of operations does not correspond " +
//                                                "to the number of operands. Each operator must has " +
//                                                "a fixed number of operands.");
//        }
//        Double result = stack.pop();
//        historyDAO.save(result.toString(), inputExpression);
//        result = isDivideByZero(result, inputExpression);
//        return result;
//    }


//    /**
//     * Checks whether the division by zero.
//     *
//     * @param result of calculation
//     * @param inputExpression - the expression entered by the user
//     * @return double result
//     * @throws ArithmeticException thrown when expression include "divide by zero"
//     */
//    private Double isDivideByZero(final Double result, final String inputExpression) {
//        if ((result.equals(Infinity)&&inputExpression.contains("^(-"))&&!inputExpression.contains("^(-0")) {
//            throw new ArithmeticException("If the exponent is negative, the power of zero (0^n, where n < 0) " +
//                    "is undefined, because division by zero is implied. ");
//        } else if (result.equals(Infinity)||result.equals(NaN)) {
//            throw new ArithmeticException("Don't divide by zero.");
//        } else return result;
//    }
}
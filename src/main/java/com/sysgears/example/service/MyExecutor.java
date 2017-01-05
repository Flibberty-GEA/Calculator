package com.sysgears.example.service;

import com.sysgears.example.domain.members.Member;
import com.sysgears.example.domain.members.Number;
import com.sysgears.example.history.HistoryDAO;

import java.util.*;


/**
 * Service class for calculating result of expression.
 *
 * @author Yevgen Goliuk
 */
public class MyExecutor {
    private HistoryDAO historyDAO;
    private BracketsParser bracketsParser;

    public MyExecutor(final HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
        this.bracketsParser = new BracketsParser();
    }

    /**
     * Executes user's expression.
     *
     * @param userExpression input expression for calculating
     * @return result of input expression
     */
    public Double execute(final String userExpression) {
        String expression = userExpression.replaceAll("\\s{2,}", " ");
        final String[] expressionArray = expression.split(" ");
        List<Member> membersList = ExpressionUtil.toListOfMembers(expressionArray);
        Double result = ((Number) bracketsParser.brackets(membersList).get(0)).getDoubleValue();
        historyDAO.save(result.toString(), userExpression);
        return result;
    }
}

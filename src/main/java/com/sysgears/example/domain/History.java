package com.sysgears.example.domain;

import java.util.*;

/**
 * Stores information about the history of calculations
 *
 * @author  Yevgen Goliuk
 */
public class History {

    /**
     * storage of result records which have been calculated
     */
    private final List<String> recordsOfResult = new LinkedList<>();

    /**
     * @return all records from storage
     */
    public List <String> getAll() {
        return recordsOfResult;
    }


    /**
     * Adds record to storage
     *
     * @param result is a result of expression which need to add to storage
     * @param expression is a expression which need to add to storage
     */
    public void save(final String result, final String expression) {
        recordsOfResult.add(expression+"="+result);
    }
}

//    private final List<Record> recordsOfResult = new LinkedList<>();

//    public List <String> getAll() {
//        List <String> result = new LinkedList<>();
//        for (Record record:recordsOfResult) {
//            result.add(record.toString());
//        }
//        return result;
//    }

//    public void save(final String result, final String expression) {
//        Record record = new Record(result, expression);
//        recordsOfResult.add(record);
//    }

//    private class Record{
//        private final String result;
//        private final String expression;
//
//        public Record(String result, String expression) {
//            this.result = result;
//            this.expression = expression;
//        }
//
//        public String getResult() {
//            return result;
//        }
//
//        public String getExpression() {
//            return expression;
//        }
//
//        @Override
//        public String toString(){
//            return expression+"="+result;
//        }
//    }
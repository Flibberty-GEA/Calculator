package com.sysgears.example.history;

import java.util.*;

/**
 * Stores information about the history of calculations.
 *
 * @author  Yevgen Goliuk
 */
public class History {

    /**
     * Storage of result records which have been calculated.
     */
    private final List<String> recordsOfResult = new LinkedList<>();

    /**
     * Get all records from storage.
     *
     * @return all records from storage
     */
    public List <String> getAll() {
        return recordsOfResult;
    }


    /**
     * Add a record to storage.
     *
     * @param result is a result of expression which need to add to storage
     * @param expression is a expression which need to add to storage
     */
    public void save(final String result, final String expression) {
        recordsOfResult.add(expression+" = "+result);
    }
}
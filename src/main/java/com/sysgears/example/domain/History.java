package com.sysgears.example.domain;

import java.util.LinkedList;
import java.util.List;

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
    public List<String> getAll() {
        return new LinkedList<>(recordsOfResult);
    }

    /**
     * Adds record to storage
     *
     * @param record is a record which need to add to storage
     */
    public void save(final String record) {
        recordsOfResult.add(record);
    }
}

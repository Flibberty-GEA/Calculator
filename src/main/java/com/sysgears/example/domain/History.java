package com.sysgears.example.domain;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class History {

    /**
     * storage of result records which have been calculated
     */
    private final List<String> recordsOfRequests  = new LinkedList<>();


    /**
     *
     * @return all records from storage
     */
    public List<String> getAllRecords() {
        return new LinkedList<>(recordsOfRequests);
    }

    /**
     *
     * @return unique records from storage
     */
    public List<String> getUniqueRecords() {
        return new LinkedList<>(new LinkedHashSet<>(recordsOfRequests));
    }

    /**
     * Adds record to storage
     *
     * @param record is a record which need to add to storage
     */
    public void saveRecord(final String record) {
        recordsOfRequests.add(record);
    }
}

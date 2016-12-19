package com.sysgears.example.service;

import com.sysgears.example.domain.History;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides an abstract interface to persistence mechanism of calculator's history .
 *
 * @author  Yevgen Goliuk
 */
public class HistoryDAO {

    private final History history = new History();


    /**
     * @return all records from storage
     */
    public String getAll() {return history.getAll().toString();}

    /**
     * @return unique records from storage
     */
    public String getUnique(){
        List <String> uniqueList = new LinkedList<>(new LinkedHashSet<>(history.getAll()));
        return uniqueList.toString();
    }

    /**
     * Adds record to storage
     *
     * @param record is a record which need to add to storage
     */
    public void save(final String record) {
        history.save(record);
    }
}

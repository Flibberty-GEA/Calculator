package com.sysgears.example.service;

import com.sysgears.example.domain.History;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yevgen on 15.12.16.
 */
public class HistoryDAO {

    /* initialize history*/
    private final History history = new History();


    /**
     *
     * @return all records from storage
     */
    public String getAll() {return history.getAll().toString();}

    /**
     *
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

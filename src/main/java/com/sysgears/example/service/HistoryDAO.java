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
    public String getAll() {return parse(history.getAll());}

    /**
     * @return unique records from storage
     */
    public String getUnique(){
        List <String> uniqueList = new LinkedList<>(new LinkedHashSet<>(history.getAll()));
        return parse(uniqueList);
    }

    /**
     * Adds record to storage
     *
     * @param result is a result of expression which need to add to storage
     * @param expression is a expression which need to add to storage
     */
    public void save(final String result, final String expression) {
        history.save(result, expression);
    }

    private String parse(final List<String> str){
        String result = "";
        for (int i=0;i < str.size();i++) {
            result = result+str.get(i)+"\n";
        }
        return result;
    }
}

package com.sysgears.example.history;

import com.sysgears.example.history.History;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides an abstract interface to persistence mechanism of calculator's history.
 *
 * @author  Yevgen Goliuk
 */
public class HistoryDAO {

    private final History history = new History();

    /**
     * Get all records from storage.
     *
     * @return string which includes all records from storage
     */
    public String getAll() {return parse(history.getAll());}

    /**
     * Get unique records from storage.
     *
     * @return string which includes only unique records from storage
     */
    public String getUnique(){
        List <String> uniqueList = new LinkedList<>(new LinkedHashSet<>(history.getAll()));
        return parse(uniqueList);
    }

    /**
     * Add a record to storage.
     *
     * @param result is a result of expression which need to add to storage
     * @param expression is a expression which need to add to storage
     */
    public void save(final String result, final String expression) {
        history.save(result, expression);
    }

    /**
     * Returns a string for a list of history records.
     *
     * @param records records from storage
     * @return string which includes records from storage
     */
    private String parse(final List<String> records){
        String result = "";
        for (String record:records) {
            result+=record+"\n";
        }
        return result;
    }
}

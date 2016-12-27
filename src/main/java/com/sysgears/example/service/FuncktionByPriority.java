package com.sysgears.example.service;


import com.sysgears.example.domain.members.symbols.operators.Function;

import java.util.Comparator;

/**
 * @author Yevgen Goliuk
 */
public class FuncktionByPriority implements Comparator<Function> {

    @Override
    public int compare(Function firstFunction, Function secondFunction){

        final int priorityFirstFun = firstFunction.getPriority();

        final int prioritySecondFun = secondFunction.getPriority();

        final int result;

        if (priorityFirstFun < prioritySecondFun) {
            result = 1;
        } else if (priorityFirstFun > prioritySecondFun) {
            result = -1;
        } else {
            result = 0;
        }

        return result;
    }
}

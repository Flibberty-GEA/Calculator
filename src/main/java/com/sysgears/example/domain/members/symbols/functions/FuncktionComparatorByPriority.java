package com.sysgears.example.domain.members.symbols.functions;


import java.util.Comparator;

/**
 * Imposes a total ordering of Functions by priority of calculation.
 * <p>
 * A comparison function, which imposes a total ordering
 * on a collection of Functions by priority of calculation.
 * Comparators can be passed to a sort method such as Collections.sort() or
 * Arrays.sort() to allow precise control over the sort order.
 *
 * @author Yevgen Goliuk
 */
public class FuncktionComparatorByPriority implements Comparator<Function> {

    /**
     * Compares its two Functions for order.
     * <p>
     * Returns a negative integer, zero, or a positive integer
     * as the first argument is less than, equal to, or greater than the second.
     *
     * @param firstFunction  the first Function to be compared
     * @param secondFunction the second Function to be compared
     * @return a negative integer, zero, or a positive integer as the
     * first Function is less than, equal to, or greater than the
     * second by a priority of calculating
     */
    @Override
    public int compare(final Function firstFunction, final Function secondFunction) {
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

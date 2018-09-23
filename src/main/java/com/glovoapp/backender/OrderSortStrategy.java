package com.glovoapp.backender;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * This is a strategy pattern for getting different sorting strategies
 */
public interface OrderSortStrategy {
    /**
     * This mthod if for comparing elements in sorting
     * @param courier the parameter for sorting in some cases
     * @return a comparator for sorting
     */
    public Comparator<Order> comparByPrioriy(Courier courier);
}

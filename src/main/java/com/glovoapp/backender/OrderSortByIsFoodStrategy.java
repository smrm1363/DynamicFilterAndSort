package com.glovoapp.backender;

import java.util.Comparator;

/**
 * This is a implemented strategy for sorting order by if is food
 */
public class OrderSortByIsFoodStrategy implements OrderSortStrategy {
    /**
     *
     * @param courier the parameter for sorting in some cases
     * @return a comparator for sorting
     */
    @Override
    public Comparator<Order> comparByPrioriy(Courier courier) {
        Comparator<Order> comparator = (order1, order2) -> order2.getFood().compareTo(order1.getFood());
        return comparator;
    }
}

package com.glovoapp.backender;

import java.util.Comparator;

/**
 * This is a sorting strategy for sorting by VIP person
 */
public class OrderSortByVipStrategy implements OrderSortStrategy {
    /**
     *
     * @param courier the parameter for sorting in some cases
     * @return a comparator for sorting
     */
    @Override
    public Comparator<Order> comparByPrioriy(Courier courier) {
        Comparator<Order> comparator = (order1, order2) -> order2.getVip().compareTo(order1.getVip());
        return comparator;
    }
}

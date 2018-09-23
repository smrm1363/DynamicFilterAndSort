package com.glovoapp.backender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderSortByIsFoodStrategyTest {
    OrderSortByIsFoodStrategy orderSortByIsFoodStrategy = new OrderSortByIsFoodStrategy();
    @Test
    void comparByPrioriy() {
        Order order1 = new Order().withId("order-1")
                .withDescription("I want a pizza cut into very small slices")
                .withFood(true)
                .withVip(false)
                .withPickup(new Location(41.3965463, 20.1963997))
                .withDelivery(new Location(41.407834, 2.1675979));
        Order order2 = new Order().withId("order-2")
                .withDescription("I want a pizza cut into very small slices")
                .withFood(false)
                .withVip(false)
                .withPickup(new Location(41.3965463, 20.1963997))
                .withDelivery(new Location(41.407834, 2.1675979));
        int result = orderSortByIsFoodStrategy.comparByPrioriy(null).compare(order1,order2);
        assertEquals(-1,result);
        result = orderSortByIsFoodStrategy.comparByPrioriy(null).compare(order2,order1);

        assertEquals(1,result);
    }

}
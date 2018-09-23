package com.glovoapp.backender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderSortByVipStrategyTest {
    OrderSortByVipStrategy orderSortByVipStrategy = new OrderSortByVipStrategy();
    @Test
    void comparByPrioriy() {
        Order order1 = new Order().withId("order-1")
                .withDescription("I want a pizza cut into very small slices")
                .withFood(true)
                .withVip(true)
                .withPickup(new Location(41.3965463, 20.1963997))
                .withDelivery(new Location(41.407834, 2.1675979));
        Order order2 = new Order().withId("order-2")
                .withDescription("I want a pizza cut into very small slices")
                .withFood(false)
                .withVip(false)
                .withPickup(new Location(41.3965463, 20.1963997))
                .withDelivery(new Location(41.407834, 2.1675979));
        int result = orderSortByVipStrategy.comparByPrioriy(null).compare(order1,order2);
        assertEquals(-1,result);
        result = orderSortByVipStrategy.comparByPrioriy(null).compare(order2,order1);

        assertEquals(1,result);
    }

}
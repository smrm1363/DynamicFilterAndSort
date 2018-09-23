package com.glovoapp.backender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderSortByDistanceStrategyTest {
    OrderSortByDistanceStrategy orderSortByDistanceStrategy = new OrderSortByDistanceStrategy();
    @Test
    void comparByPrioriy() {
        Courier courier = new Courier().withId("courier-1")
                .withBox(true)
                .withName("Manolo Escobar")
                .withVehicle(Vehicle.MOTORCYCLE)
                .withLocation(new Location(41.3965463, 2.1963997));

        Order order1 = new Order().withId("order-1")
                .withDescription("I want a pizza cut into very small slices")
                .withFood(true)
                .withVip(false)
                .withPickup(new Location(41.3965463, 20.1963997))
                .withDelivery(new Location(41.407834, 2.1675979));
        Order order2 = new Order().withId("order-2")
                .withDescription("I want a pizza cut into very small slices")
                .withFood(true)
                .withVip(false)
                .withPickup(new Location(41.3965463, 2.19639978))
                .withDelivery(new Location(41.407834, 2.1675979));
        int result = orderSortByDistanceStrategy.comparByPrioriy(courier).compare(order1,order2);
        assertEquals(-1,result);
        result = orderSortByDistanceStrategy.comparByPrioriy(courier).compare(order2,order1);
        assertEquals(1,result);
        result = orderSortByDistanceStrategy.comparByPrioriy(courier).compare(order1.withPickup(new Location(41.3965463, 2.19639978)),order2);
        assertEquals(0,result);

    }

}
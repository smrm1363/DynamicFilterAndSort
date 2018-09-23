package com.glovoapp.backender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

class HideByOrderConteentStrategyTest {
    HideByOrderConteentStrategy hideByOrderConteentStrategy = new HideByOrderConteentStrategy();

    @Test
    void hideByRul() {
        Courier courier = new Courier().withId("courier-1")
                .withBox(true)
                .withName("Manolo Escobar")
                .withVehicle(Vehicle.MOTORCYCLE)
                .withLocation(new Location(41.3965463, 2.1963997));

        Order order = new Order().withId("order-1")
                .withDescription("I want a pizza cut into very small slices")
                .withFood(true)
                .withVip(false)
                .withPickup(new Location(41.3965463, 2.1963997))
                .withDelivery(new Location(41.407834, 2.1675979));

       assertEquals(true, hideByOrderConteentStrategy.hideByRul(courier).test(order));
       assertEquals(false, hideByOrderConteentStrategy.hideByRul(courier.withBox(false)).test(order));

    }

}
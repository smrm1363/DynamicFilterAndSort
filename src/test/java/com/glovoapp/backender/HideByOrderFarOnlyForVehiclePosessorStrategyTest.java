package com.glovoapp.backender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HideByOrderFarOnlyForVehiclePosessorStrategyTest {
    HideByOrderFarOnlyForVehiclePosessorStrategy hideByOrderFarOnlyForVehiclePosessorStrategy = new HideByOrderFarOnlyForVehiclePosessorStrategy();
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
                .withPickup(new Location(41.3965463, 20.1963997))
                .withDelivery(new Location(41.407834, 2.1675979));

        assertEquals(false, hideByOrderFarOnlyForVehiclePosessorStrategy.hideByRul(courier).test(order));
        assertEquals(true, hideByOrderFarOnlyForVehiclePosessorStrategy.hideByRul(courier).test(order.withPickup(new Location(41.3965463, 2.1963998))));
        assertEquals(true, hideByOrderFarOnlyForVehiclePosessorStrategy.hideByRul(courier.withVehicle(Vehicle.BICYCLE)).test(order.withPickup(new Location(41.3965463, 20.1963998))));
    }

}
package com.glovoapp.backender;

import org.springframework.beans.factory.annotation.Value;

import java.util.function.Predicate;

/**
 * The implement strategy of HideMakerStrategy for filtering distant pickup place only for courier with specific type of vehicle
 */
public class HideByOrderFarOnlyForVehiclePosessorStrategy implements HideMakerStrategy {

    /**
     * These strings are used for getting data from property file, we can change the data in property file
     */
    private String permitedVehicle;
    private String howFar;

    public HideByOrderFarOnlyForVehiclePosessorStrategy() {
        permitedVehicle= Util.getProperty("hide.strategy.permitedVehicle");
        howFar= Util.getProperty("hide.strategy.howFar");
    }

    /**
     *
     * @param courier which is the parameter for filtering
     * @return a Predicate which can be used for filtering
     */
    @Override
    public Predicate<Order> hideByRul(Courier courier) {
        /**
         * order is the destination object for filtering
         */
        return order -> {

            /**
             * Separates different text which we want to be used in filtering for distant pickup place  for specific vehicle type
             */
            String permitedVehilesForFilter[] = permitedVehicle.split(",");

            Boolean hasCondition = true;
            for (int x = 0; x < permitedVehilesForFilter.length; x++) {
                if(DistanceCalculator.calculateDistance(courier.getLocation(),order.getPickup()) > Double.valueOf(howFar))
                {
                    hasCondition = false;
                    if(courier.getVehicle().toString().equalsIgnoreCase(permitedVehilesForFilter[x]))
                    {
                        hasCondition = true;
                        return hasCondition;
                    }
                }

                }
            return hasCondition;
        };



    }
}

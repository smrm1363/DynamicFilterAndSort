package com.glovoapp.backender;

import java.util.Comparator;

/**
 * This is an implemented strategy for sorting by distance to pickup place
 */
public class OrderSortByDistanceStrategy implements OrderSortStrategy {
    /**
     * This is a property for getting a variety of distance slots dynamically
     */
    private String distanceSlotsProperty;

    public OrderSortByDistanceStrategy() {
        distanceSlotsProperty = Util.getProperty("sort.distance.slot");;
    }

    /**
     *
     * @param courier the parameter for sorting in some cases
     * @return a comarator for sorting
     */
    @Override
    public Comparator<Order> comparByPrioriy(Courier courier) {
        /**
         * This is an array of distance slots
         */
        String distanceSlots[] = distanceSlotsProperty.split(",");

        Comparator<Order> comparator = (order1, order2) -> {
            Integer priorityOrder1 = isBetween(distanceSlots,order1,courier);
            Integer priorityOrder2 = isBetween(distanceSlots,order2,courier);
            return priorityOrder2.compareTo(priorityOrder1);
        };
        return comparator;
    }

    /**
     * For finding a distance slot. For instance, between 0 to 500 is slot 1, 500 to 1000 slot 2
     * @param distanceSlots is an array shoes limits of all slots
     * @param order for finding the pickup place of order
     * @param courier for finding the current place of courier
     * @return the slot
     */
    private int isBetween(String distanceSlots[],Order order,Courier courier)
    {
        for(int x=0;x<distanceSlots.length;x++)
        {
            if(distanceSlots.length>x+1)
            {
                if (DistanceCalculator.calculateDistance(order.getPickup(), courier.getLocation()) < Double.valueOf(distanceSlots[x+1]))
                    return x;
            }
            else
            {
                return x;
            }

        }
        return 0;
    }
}

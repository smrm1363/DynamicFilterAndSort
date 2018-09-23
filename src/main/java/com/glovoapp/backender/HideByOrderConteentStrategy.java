package com.glovoapp.backender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

/**
 * A kind of strategy for HideMakerStrategy interface
 */
public class HideByOrderConteentStrategy implements HideMakerStrategy {
    /**
     * It is used for getting filters from property file dynamically
     */
    private String contentContextFilter;

    public HideByOrderConteentStrategy() {
        contentContextFilter= Util.getProperty("hide.strategy.contentContext.filter");
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
             * Separates diffrent text which we want to be used in filtering in the order description
             */
            String contentContextForFilter[] = contentContextFilter.split(",");
            Boolean hasCondition = true;
            for (int x = 0; x < contentContextForFilter.length; x++) {
                if (order.getDescription().toLowerCase().contains(contentContextForFilter[x].toLowerCase()))
                    hasCondition = false;
            }
            return hasCondition ? hasCondition : courier.getBox();
        };



    }
}

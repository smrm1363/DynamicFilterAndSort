package com.glovoapp.backender;

import java.util.function.Predicate;

/**
 * This is an interface for Strategy design pattern.
 */
public interface HideMakerStrategy {
    /**
     * For filtring order
     * @param courier which is the parameter for filtering
     * @return a Predicate which can be used for filtering
     */
    public Predicate<Order> hideByRul(Courier courier);
}

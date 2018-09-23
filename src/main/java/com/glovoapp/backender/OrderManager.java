package com.glovoapp.backender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class is the context of gathering our logic
 */
@Component
public class OrderManager {
    @Value("${hide.strategy.classes}")
    private String hideStrategyClasses;
    @Value("${sort.strategy.classes}")
    private String sortStrategyClasses;
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderManager(CourierRepository courierRepository, OrderRepository orderRepository) {

        this.courierRepository = courierRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * For calling from service layer. It finds orders by courierId with special filters and orders
     * @param courierId is the ID of the courier
     * @return a list of OrderVM
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public List<OrderVM> findOrderVMByCourierId(String courierId) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Courier courier = courierRepository.findById(courierId);
        /**
         * It is the final Predicate for our filtering
         */
        Predicate<Order> orderPredicate = null;
        /**
         * It is the final Comparator for our sorting
         */
        Comparator<Order> orderComparator = null;
        /**
         * This is a list of all dynamic filtering strategies
         */
        List<HideMakerStrategy> hideMakerStrategyList = getHidePredicateList();
        /**
         * This is a list of all dynamic sorting strategies
         */
        List<OrderSortStrategy> orderSortStrategyList = getOrderSortStrategyList();

        /**
         * Bellow code is for making order predicate dynamically. We create a chain of predicates
         */
        if(hideMakerStrategyList.size()>0)
        {
            orderPredicate = hideMakerStrategyList.get(0).hideByRul(courier);
            for (int x=1;x<hideMakerStrategyList.size();x++)
            {
                orderPredicate = orderPredicate.and(hideMakerStrategyList.get(x).hideByRul(courier));
            }


        }

        /**
         * Bellow code is for making order comparator dynamically. We create a chain of comparators
         */
        if(orderSortStrategyList.size()>0)
        {
            orderComparator = orderSortStrategyList.get(0).comparByPrioriy(courier);
            for (int x=1;x<orderSortStrategyList.size();x++)
            {
                orderComparator = orderComparator.thenComparing(orderSortStrategyList.get(x).comparByPrioriy(courier));
            }
        }

        /**
         * After finding our predicates and comparators, it is timed to enforce them on order list
         */
        return orderRepository.findAll()
                .stream().filter(orderPredicate).sorted(orderComparator)
                .map(order -> new OrderVM(order.getId(), order.getDescription()))
                .collect(Collectors.toList());

    }


    /**
     * For getting a list of predicates for filtering.
     * @return a list of predicates for filtering
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private List<HideMakerStrategy> getHidePredicateList() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String contentContextForFilter[] = hideStrategyClasses.split(",");
        List<HideMakerStrategy> hideMakerStrategyList = new ArrayList<>();
        for (int x=0;x<contentContextForFilter.length;x++)
        {

            hideMakerStrategyList.add((HideMakerStrategy) Class.forName(contentContextForFilter[x]).newInstance());
        }
        return hideMakerStrategyList;

    }

    /**
     * For getting a list of comparators for sorting.
     * @return a list of comparators for sorting
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private List<OrderSortStrategy> getOrderSortStrategyList() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String sortStrategyies[] = sortStrategyClasses.split(",");
        List<OrderSortStrategy> OrderSortStrategyList = new ArrayList<>();
        for (int x=0;x<sortStrategyies.length;x++)
        {

            OrderSortStrategyList.add((OrderSortStrategy) Class.forName(sortStrategyies[x]).newInstance());
        }
        return OrderSortStrategyList;

    }






}

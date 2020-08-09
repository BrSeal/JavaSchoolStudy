package main.services;

import main.model.logistic.Order;

import java.util.List;

public interface OrderService {
    int save(Order waypoint);

    List<Order> getAll();

    Order get(int it);

    Order delete(int id);

    Order delete(Order order);

    void update(Order order);
}

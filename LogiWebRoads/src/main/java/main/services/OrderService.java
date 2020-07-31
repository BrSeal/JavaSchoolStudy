package main.services;

import main.model.logistic.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    Order get(int id);

    int save(Order order);

    Order delete(int id);

    Order delete(Order order);
}

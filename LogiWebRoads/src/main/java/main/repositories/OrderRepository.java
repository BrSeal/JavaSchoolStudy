package main.repositories;

import main.model.logistic.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();

    Order get(int id);

    int save(Order order);

    void update(Order order);

    Order delete(int id);

    Order delete(Order order);
}

package main.services;

import main.model.logistic.Order;
import main.model.logistic.OrderDTO;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order get(int it);

    int save(OrderDTO order);

    void update(OrderDTO order);

    Order delete(int id);

    Order delete(Order order);
}

package main.repositories;

import main.model.logistic.Order;
import main.model.users.Driver;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();

    Order get(int id);

    Order getOrderByDriver(Driver d);

    int save(Order order);

    void update(Order order);

    Order delete(int id);

    Order delete(Order order);


}

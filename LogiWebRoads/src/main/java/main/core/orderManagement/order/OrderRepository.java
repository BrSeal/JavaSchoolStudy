package main.core.orderManagement.order;

import main.core.orderManagement.order.entity.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();

    Order get(int id);

    int save(Order order);

    void update(Order order);

    void delete(Order order);
}

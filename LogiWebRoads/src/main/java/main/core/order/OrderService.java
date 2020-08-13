package main.core.order;

import main.model.logistic.orderAndWaypoint.Order;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAll();

    OrderDTO get(int it);

    int save(OrderDTO order);

    void update(OrderDTO order);

    Order delete(int id);

    Order delete(Order order);
}

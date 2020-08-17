package main.core.order;

import main.core.order.DTO.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAll();

    OrderDTO get(int it);

    int save(OrderDTO order);

    void assignVehicle(OrderDTO order);

    void assignDrivers(OrderDTO order);
}

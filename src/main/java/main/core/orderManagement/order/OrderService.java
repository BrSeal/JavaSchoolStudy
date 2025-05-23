package main.core.orderManagement.order;

import main.core.orderManagement.order.DTO.*;

import java.util.List;

public interface OrderService {
    List<SmallInfoOrderDTO> getAll();

    InfoOrderDTO get(int it);

    int save(NewOrderDTO order);

    void assignVehicle(AssignVehicleOrderDTO order);

    void assignDrivers(AssignDriversOrderDTO order);
}

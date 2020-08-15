package main.core.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.Order;
import main.model.logistic.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignVehicleOrderDTO implements OrderDTO {
    private static final String NO_VEHICLE_ASSIGNED_ERR = "No vehicles assigned! Please assign vehicle!";
    private static final int NOTING = 0;

    private int id;
    private int vehicleId;

    @Override
    public Order toOrder() {
        if (vehicleId == 0) throw new IllegalArgumentException(NO_VEHICLE_ASSIGNED_ERR);

        Order order = new Order();
        order.setId(id);
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        order.setAssignedVehicle(vehicle);

        return order;
    }
}

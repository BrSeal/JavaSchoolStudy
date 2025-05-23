package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignVehicleOrderDTO{
    private static final String NO_VEHICLE_ASSIGNED_ERR = "No vehicles assigned! Please assign vehicle!";
    private static final int NOTING = 0;

    private int id;
    private int vehicleId;
}

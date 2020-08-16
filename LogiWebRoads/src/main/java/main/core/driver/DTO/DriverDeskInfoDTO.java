package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.waypoint.WaypointDTO;
import main.model.logistic.Order;
import main.model.users.Driver;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDeskInfoDTO implements DriverDTO{

    private int id;
    private List<Integer> drivers;
    private String vehicleRegNum;
    private int orderId;
    List<WaypointDTO> waypoints;

    public DriverDeskInfoDTO(Driver driver){
        Order order=driver.getCurrentOrder();

        id=driver.getId();

        if(order!=null) {
            orderId = order.getId();
            vehicleRegNum = order.getAssignedVehicle().getRegNumber();
            drivers = order.getAssignedDrivers().stream()
                    .filter(d -> d.getId() != id)
                    .map(Driver::getId)
                    .collect(Collectors.toList());
            waypoints = order.getWaypoints().stream()
                    .map(WaypointDTO::new)
                    .collect(Collectors.toList());
        }
    }
    @Override
    public Driver toDriver() {
       return null;
    }
}
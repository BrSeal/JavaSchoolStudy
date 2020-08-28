package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.orderManagement.waypoint.WaypointDTO;
import main.global.exceptionHandling.exceptions.DtoConvertForbiddenException;
import main.core.orderManagement.order.entity.Order;
import main.core.driver.entity.Driver;
import main.core.driver.entity.DriverStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDeskInfoDTO {

    private int id;
    private DriverStatus status;
    private List<Integer> drivers;
    private String vehicleRegNum;
    private int orderId;
    private List<WaypointDTO> waypoints;
    private int currentCity;

    public DriverDeskInfoDTO(Driver driver){
        Order order=driver.getCurrentOrder();

        id=driver.getId();
        status=driver.getStatus();
        currentCity=driver.getCurrentCity().getId();
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
}

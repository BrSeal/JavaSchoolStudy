package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.driver.entity.Driver;
import main.core.driver.entity.DriverStatus;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.waypoint.entity.Waypoint;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDeskInfoDTO {

    private int id;
    private int hoursWorked;
    private String firstName;
    private String lastName;

    private DriverStatus status;
    private List<Driver> drivers;
    private String vehicleRegNum;
    private int orderId;
    private Waypoint currentTarget;
    private List<Waypoint> waypoints;

    public DriverDeskInfoDTO(Driver driver, List<Driver> companions, Waypoint currentTarget) {
        Order order = driver.getCurrentOrder();

        id = driver.getId();
        hoursWorked = driver.getHoursWorked();
        firstName = driver.getFirstName();
        lastName = driver.getLastName();
        status = driver.getStatus();
        if (order != null) {
            orderId = order.getId();
            vehicleRegNum = order.getAssignedVehicle().getRegNumber();
            waypoints = order.getWaypoints();
            drivers = companions.stream().filter(d->d.getId()!=id).collect(Collectors.toList());
            this.currentTarget=currentTarget;
        }
    }
}

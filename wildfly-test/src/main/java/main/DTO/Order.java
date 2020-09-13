package main.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private String creationDate;
    private OrderStatus status;
    private Vehicle assignedVehicle;
    private List<Driver> drivers;
    private List<Waypoint> waypoints;
}

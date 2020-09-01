package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.orderManagement.waypoint.WaypointDTO;
import main.core.orderManagement.order.entity.Order;
import main.core.driver.entity.Driver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoOrderDTO {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private int id;
    private OrderStatus status;
    private String creationDate;
    private List<WaypointDTO> waypoints;
    private List<Integer> assignedDrivers;
    private int assignedVehicle;

    public InfoOrderDTO(Order o) {
        id = o.getId();
        status = o.getStatus();
        creationDate = dateFormat.format(o.getCreationDate());
        assignedVehicle = o.getAssignedVehicle() == null ? 0 : o.getAssignedVehicle().getId();

        waypoints = o.getWaypoints().stream()
                .map(WaypointDTO::new)
                .collect(Collectors.toList());                                                                         

        if (o.getAssignedDrivers() == null) {
            assignedDrivers = new ArrayList<>();
        } else {
            assignedDrivers = o.getAssignedDrivers().stream()
                    .map(Driver::getId)
                    .collect(Collectors.toList());
        }
    }
}

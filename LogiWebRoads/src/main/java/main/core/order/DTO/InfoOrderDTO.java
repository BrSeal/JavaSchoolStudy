package main.core.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.waypoint.WaypointDTO;
import main.model.logistic.Order;
import main.model.users.Driver;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoOrderDTO implements OrderDTO {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private int id;
    private boolean isCompleted;
    private String creationDate;
    private List<WaypointDTO> waypoints;
    private int currentOrder;
    private List<Integer> assignedDrivers;
    private int assignedVehicle;

    public InfoOrderDTO(Order o) {
        id = o.getId();
        isCompleted = o.isCompleted();
        creationDate = dateFormat.format(o.getCreationDate());
        assignedVehicle = o.getAssignedVehicle() == null ? 0 : o.getAssignedVehicle().getId();

        waypoints = o.getWaypoints().stream()
                .map(WaypointDTO::new)
                .collect(Collectors.toList());

        if (o.getAssignedDrivers() == null) {
            assignedDrivers = null;
            o.getAssignedDrivers().stream()
                    .map(Driver::getId)
                    .collect(Collectors.toList());
        } else {
            assignedDrivers = o.getAssignedDrivers().stream()
                    .map(Driver::getId)
                    .collect(Collectors.toList());
        }
    }

    public Order toOrder() {
        Order o = new Order();

        return o;
    }


}

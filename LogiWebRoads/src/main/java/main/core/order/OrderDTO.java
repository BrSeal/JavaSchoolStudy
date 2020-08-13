package main.core.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.orderAndWaypoint.Order;
import main.model.logistic.orderAndWaypoint.Waypoint;
import main.core.waypoint.WaypointDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private int id;
    private boolean isCompleted;
    private List<WaypointDTO> waypoints;

    public OrderDTO(Order o) {
        this.id = o.getId();
        this.isCompleted = o.isCompleted();
        this.waypoints = o.getWaypoints().stream()
                .map(WaypointDTO::new)
                .collect(Collectors.toList());
    }

    public Order toOrder(){
        List<Waypoint> waypoints = this.waypoints.stream()
                .map(WaypointDTO::toWaypoint)
                .collect(Collectors.toList());

        if(validate(waypoints)) throw new IllegalArgumentException();

        waypoints=calculateAndOptimizeRoute(waypoints);

        Date date=id==0?new Date():null;

        Order o = new Order(date, isCompleted, waypoints);
        o.setId(id);

        return o;
    }

    private List<Waypoint> calculateAndOptimizeRoute(List<Waypoint> waypoints){
        return waypoints;
    }

    private boolean validate(List<Waypoint> waypoints){
        return true;
    }
}

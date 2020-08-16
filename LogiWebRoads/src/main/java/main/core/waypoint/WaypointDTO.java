package main.core.waypoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaypointDTO {

    private int id;
    private int cargo;
    private int order;
    private int cityId;
    private int pathIndex;
    private int pathLength;
    private boolean isDone;
    private WaypointType type;

    public WaypointDTO(Waypoint w) {
        id = w.getId();
        cargo = w.getCargo().getId();
        order = w.getOrder().getId();
        cityId = w.getCity().getId();
        type = w.getType();
        pathIndex = w.getPathIndex();
        pathLength= w.getPathLength();
        order = w.getOrder().getId();
    }

    public Waypoint toWaypoint() {
        City city = new City();
        city.setId(this.cityId);

        Cargo cargo = new Cargo();
        cargo.setId(this.cargo);

        Order order = new Order();
        order.setId(this.order);

        Waypoint w = new Waypoint(city, cargo, type, pathIndex, pathLength, isDone, order);
        w.setId(id);

        return w;
    }


}

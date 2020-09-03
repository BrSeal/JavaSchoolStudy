package main.core.orderManagement.waypoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.orderManagement.waypoint.entity.WaypointType;

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
    private boolean done;
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
        done=w.isDone();
    }
}

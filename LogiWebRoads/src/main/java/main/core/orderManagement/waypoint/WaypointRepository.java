package main.core.orderManagement.waypoint;


import main.core.cargo.entity.Cargo;
import main.core.orderManagement.waypoint.entity.Waypoint;

import java.util.List;

public interface WaypointRepository {
    int save(Waypoint waypoint);

    void update(Waypoint waypoint);

    List<Waypoint> getAll();

    List<Waypoint> getByCargo(Cargo cargo);

    Waypoint get(int it);

    Waypoint delete(Waypoint waypoint);
}

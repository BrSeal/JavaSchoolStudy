package main.core.orderManagement.waypoint;


import main.core.orderManagement.cargo.entity.Cargo;
import main.core.orderManagement.waypoint.entity.Waypoint;

import java.util.List;

public interface WaypointRepository {
    void update(Waypoint waypoint);

    List<Waypoint> getAll();

    List<Waypoint> getByCargo(Cargo cargo);

    Waypoint get(int it);
}

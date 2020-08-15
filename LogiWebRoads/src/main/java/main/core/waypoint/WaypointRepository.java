package main.core.waypoint;


import main.model.logistic.Cargo;
import main.model.logistic.Waypoint;

import java.util.List;

public interface WaypointRepository {
    int save(Waypoint waypoint);

    void update(Waypoint waypoint);

    List<Waypoint> getAll();

    List<Waypoint> getByCargo(Cargo cargo);

    Waypoint get(int it);

    Waypoint delete(Waypoint waypoint);
}

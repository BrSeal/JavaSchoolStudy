package main.repositories;


import main.model.logistic.Waypoint;

import java.util.List;

public interface WaypointRepository {
    int save(Waypoint waypoint);

    void update(Waypoint waypoint);

    void saveAll(List<Waypoint> waypoints);

    List<Waypoint> getAll();

    Waypoint get(int it);

    Waypoint delete(Waypoint waypoint);
}

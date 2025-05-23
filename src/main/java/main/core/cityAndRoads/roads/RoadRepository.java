package main.core.cityAndRoads.roads;

import main.core.cityAndRoads.roads.entity.Road;

import java.util.List;

public interface RoadRepository {
    int add(Road road);

    void addAll(List<Road> roads);

    Road get(int id);

    List<Road> getAll();

    void update(Road road);

    void delete(Road road);
}

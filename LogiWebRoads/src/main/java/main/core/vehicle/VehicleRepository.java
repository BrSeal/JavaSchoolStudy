package main.core.vehicle;

import main.core.vehicle.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> getAll();

    Vehicle get(int id);

    int save(Vehicle e);

    void update(Vehicle e);

    Vehicle delete(Vehicle Vehicle);

    List<Vehicle> getQueryResult(String hql);
}

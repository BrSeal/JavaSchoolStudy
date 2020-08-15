package main.core.vehicle;

import main.model.logistic.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> getAll();

    Vehicle get(int id);

    int save(Vehicle e);

    void update(Vehicle e);

    Vehicle delete(Vehicle Vehicle);

    List<Vehicle> getQueryResult(String hql);
}

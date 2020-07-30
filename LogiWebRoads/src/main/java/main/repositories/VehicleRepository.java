package main.repositories;

import main.model.logistic.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> getAll();

    Vehicle get(int id);

    int save(Vehicle e);

    Vehicle delete(int id);

    Vehicle delete(Vehicle Vehicle);
}

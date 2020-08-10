package main.services;

import main.model.logistic.Vehicle;
import main.model.logistic.VehicleDTO;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAll();

    Vehicle get(int id);

    int save(VehicleDTO e);

    void update(VehicleDTO vehicle);

    Vehicle delete(int id);

    Vehicle delete(Vehicle Vehicle);

    List<Vehicle> getAvailable();
}

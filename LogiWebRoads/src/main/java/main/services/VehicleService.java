package main.services;

import main.model.logistic.Vehicle;
import main.model.logistic.VehicleDTO;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAll();

    Vehicle get(int id);

    String save(VehicleDTO e);

    String update(VehicleDTO vehicle);

    String delete(int id);

    String delete(Vehicle Vehicle);

    List<Vehicle> getAvailable();
}

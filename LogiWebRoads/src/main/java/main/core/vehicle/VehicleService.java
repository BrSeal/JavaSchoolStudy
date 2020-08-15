package main.core.vehicle;

import main.model.logistic.Vehicle;

import java.util.List;

public interface VehicleService {
    List<VehicleDTO> getAll();

    VehicleDTO get(int id);

    List<VehicleDTO> getByOrderId(int order);

    List<VehicleDTO> getAvailable(int orderId);

    int save(VehicleDTO e);

    int update(VehicleDTO vehicle);

    int delete(int id);

    int delete(Vehicle Vehicle);


}

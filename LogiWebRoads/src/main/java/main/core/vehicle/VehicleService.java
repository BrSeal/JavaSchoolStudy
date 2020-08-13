package main.core.vehicle;

import main.model.logistic.Vehicle;

import java.util.List;

public interface VehicleService {
    List<VehicleDTO> getAll();

    VehicleDTO get(int id);

    List<VehicleDTO> getByOrderId(int order);

    List<VehicleDTO> getAvailable(int orderId);

    String save(VehicleDTO e);

    String update(VehicleDTO vehicle);

    String delete(int id);

    String delete(Vehicle Vehicle);


}

package main.core.vehicle;

import main.core.vehicle.DTO.NewVehicleDTO;
import main.core.vehicle.DTO.VehicleDTO;
import main.core.vehicle.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<VehicleDTO> getAll();

    VehicleDTO get(int id);

    List<VehicleDTO> getByOrderId(int order);

    List<VehicleDTO> getAvailable(int orderId);

    int save(NewVehicleDTO e);

    int update(VehicleDTO vehicle);

    int delete(int id);
}

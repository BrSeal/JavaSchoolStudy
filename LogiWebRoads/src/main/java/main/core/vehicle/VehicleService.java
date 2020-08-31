package main.core.vehicle;

import main.core.vehicle.DTO.NewVehicleDTO;
import main.core.vehicle.DTO.VehicleAssignmentToOrderDTO;
import main.core.vehicle.DTO.VehicleFullInfoDTO;
import main.core.vehicle.DTO.VehicleSmallInfoDTO;

import java.util.List;

public interface VehicleService {
    List<VehicleSmallInfoDTO> getAll();

    VehicleFullInfoDTO get(int id);

    List<VehicleFullInfoDTO> getByOrderId(int order);

    List<VehicleAssignmentToOrderDTO> getAvailable(int orderId);

    int save(NewVehicleDTO e);

    int update(VehicleFullInfoDTO vehicle);

    int delete(int id);
}

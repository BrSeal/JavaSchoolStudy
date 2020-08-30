package main.core.vehicle;

import main.core.vehicle.DTO.NewVehicleDTO;
import main.core.vehicle.DTO.VehicleFullInfoDTO;
import main.core.vehicle.DTO.VehicleSmallInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.service = vehicleService;
    }

    @GetMapping("/")
    public List<VehicleSmallInfoDTO> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public VehicleFullInfoDTO getVehicleById(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping("/assigned/{orderId}")
    public List<VehicleFullInfoDTO> getAssignedVehicles(@PathVariable int orderId) {
        return service.getByOrderId(orderId);
    }

    @GetMapping("/available/{orderId}")
    public List<VehicleFullInfoDTO> getAvailableVehicles(@PathVariable int orderId){
        return service.getAvailable(orderId);
    }

    @PostMapping("/new/")
    public int saveVehicle(@RequestBody NewVehicleDTO driver) {
        return service.save(driver);
    }

    @PutMapping("/update/")
    public int updateVehicleById(@RequestBody VehicleFullInfoDTO vehicleToUpdate) {
        return service.update(vehicleToUpdate);
    }

    @DeleteMapping("delete/{id}")
    public int deleteVehicle(@PathVariable int id) {
        return service.delete(id);
    }
}

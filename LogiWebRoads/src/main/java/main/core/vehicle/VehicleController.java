package main.core.vehicle;

import main.core.vehicle.DTO.VehicleDTO;
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
    public List<VehicleDTO> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public VehicleDTO getVehicleById(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping("/assigned/{orderId}")
    public List<VehicleDTO> getAssignedVehicles(@PathVariable int orderId) {
        return service.getByOrderId(orderId);
    }

    @GetMapping("/available/{orderId}")
    public List<VehicleDTO> getAvailableVehicles(@PathVariable int orderId){
        return service.getAvailable(orderId);
    }

    @PostMapping("/new/")
    public int saveVehicle(@RequestBody VehicleDTO driver) {
        return service.save(driver);
    }

    @PutMapping("/update/")
    public int updateVehicleById(@RequestBody VehicleDTO vehicleToUpdate) {
        return service.update(vehicleToUpdate);
    }

    @DeleteMapping("delete/{id}")
    public int deleteVehicle(@PathVariable int id) {
        return service.delete(id);
    }
}
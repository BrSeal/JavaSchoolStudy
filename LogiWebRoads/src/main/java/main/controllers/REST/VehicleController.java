package main.controllers.REST;

import main.model.logistic.Vehicle;
import main.model.logistic.VehicleDTO;
import main.services.VehicleService;
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
    public List<Vehicle> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Vehicle getVehicleById(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping("/available/")
    public List<Vehicle> getAvailableVehicles(){
        return service.getAvailable();
    }


    @PostMapping("/new/")
    public int saveVehicle(VehicleDTO driver) {
        return service.save(driver);
    }

    @PostMapping("/update/")
    public void updateVehicleById(VehicleDTO vehicleToUpdate) {
         service.update(vehicleToUpdate);
    }

    @DeleteMapping("delete/{id}")
    public Vehicle deleteVehicle(@PathVariable int id) {
        return service.delete(id);
    }
}

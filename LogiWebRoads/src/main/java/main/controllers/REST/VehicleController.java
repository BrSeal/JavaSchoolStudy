package main.controllers.REST;

import main.model.logistic.Vehicle;
import main.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.service = vehicleService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Vehicle> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Vehicle getVehicleById(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/new/")
    @ResponseBody
    public int getVehicleById(@RequestBody Vehicle driver) {
        return service.save(driver);
    }

    @PutMapping("/update/")
    @ResponseBody
    public int updateVehicleById(@RequestBody Vehicle vehicleToUpdate) {
        return service.save(vehicleToUpdate);
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public Vehicle deleteVehicle(@PathVariable int id) {
        return service.delete(id);
    }
}

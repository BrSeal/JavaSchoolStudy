package main.core.cargo;

import main.core.cargo.DTO.CargoDTO;
import main.model.logistic.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoCRUDController {
    private final CargoService service;

    @Autowired
    public CargoCRUDController(CargoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<CargoDTO> getCargos() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Cargo getCargoById(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/new/")
    public int saveCargo(@RequestBody Cargo cargo) {
        return service.save(cargo);
    }

    @PostMapping("/update/")
    public String updateCargo(@RequestBody Cargo cargo) {
        return service.update(cargo);
    }
}
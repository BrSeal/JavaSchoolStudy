package main.core.cargo;

import main.core.cargo.DTO.CargoDTO;
import main.core.cargo.DTO.UpdateStatusCargoDTO;
import main.core.cargo.entity.Cargo;
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

    @PutMapping("/update/")
    public void updateCargo(@RequestBody UpdateStatusCargoDTO cargo) {
      service.update(cargo);
    }
}
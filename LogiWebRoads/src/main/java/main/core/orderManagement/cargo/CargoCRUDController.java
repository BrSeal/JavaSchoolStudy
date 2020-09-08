package main.core.orderManagement.cargo;

import main.core.orderManagement.cargo.DTO.InfoCargoDTO;
import main.core.orderManagement.cargo.DTO.SmallCargoDTO;
import main.core.orderManagement.cargo.DTO.UpdateStatusCargoDTO;
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
    public List<SmallCargoDTO> getCargos() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public InfoCargoDTO getCargoById(@PathVariable int id) {
        return service.get(id);
    }

    @PutMapping("/updateStatus/")
    public void updateCargo(@RequestBody UpdateStatusCargoDTO cargo) {
      service.update(cargo);
    }
}
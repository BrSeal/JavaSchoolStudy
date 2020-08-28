package main.core.cargo;


import main.core.cargo.DTO.CargoDTO;
import main.core.cargo.entity.Cargo;

import java.util.List;

public interface CargoService {
    List<CargoDTO> getAll();

    Cargo get(int id);

    int save(CargoDTO e);

    void update(CargoDTO e);
}

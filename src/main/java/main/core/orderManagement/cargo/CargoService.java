package main.core.orderManagement.cargo;


import main.core.orderManagement.cargo.DTO.InfoCargoDTO;
import main.core.orderManagement.cargo.DTO.SmallCargoDTO;
import main.core.orderManagement.cargo.DTO.UpdateStatusCargoDTO;

import java.util.List;

public interface CargoService {
    List<SmallCargoDTO> getAll();

    InfoCargoDTO get(int id);

    void update(UpdateStatusCargoDTO e);
}

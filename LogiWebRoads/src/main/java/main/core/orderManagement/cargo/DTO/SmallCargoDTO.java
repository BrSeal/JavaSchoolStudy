package main.core.orderManagement.cargo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.orderManagement.cargo.entity.Cargo;
import main.core.orderManagement.cargo.entity.CargoStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmallCargoDTO {
    private int id;
    private String name;
    private int weight;
    private CargoStatus status;

    public SmallCargoDTO(Cargo cargo) {
        id=cargo.getId();
        name = cargo.getName();
        weight = cargo.getWeight();
        status = cargo.getStatus();
    }
}

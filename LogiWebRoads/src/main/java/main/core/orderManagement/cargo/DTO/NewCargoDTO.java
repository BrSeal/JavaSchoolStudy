package main.core.orderManagement.cargo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.orderManagement.cargo.entity.Cargo;
import main.core.orderManagement.cargo.entity.CargoStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCargoDTO{
    private String name;
    private int weight;

    public Cargo toCargo(){
        return new Cargo(name,weight, CargoStatus.PREPARED);
    }
}

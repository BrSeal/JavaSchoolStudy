package main.core.cargo.DTO;

import main.model.logistic.Cargo;
import main.model.logistic.CargoStatus;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusCargoDTO implements CargoDTO{

    private int id;
    private CargoStatus status;
    @Override
    public Cargo toCargo() {
        Cargo cargo=new Cargo(null,0,status);
        cargo.setId(id);
        return cargo;
    }
}

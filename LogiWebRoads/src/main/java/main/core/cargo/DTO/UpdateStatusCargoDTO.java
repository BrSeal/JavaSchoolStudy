package main.core.cargo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.Cargo;
import main.model.logistic.CargoStatus;

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

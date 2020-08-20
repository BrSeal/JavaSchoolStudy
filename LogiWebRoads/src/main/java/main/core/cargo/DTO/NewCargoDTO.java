package main.core.cargo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.Cargo;
import main.model.logistic.CargoStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCargoDTO implements CargoDTO{
    private String name;
    private int weight;

    public Cargo toCargo(){
        if(name==null||name.isEmpty()) throw new IllegalArgumentException("Cargo name is empty!");
        if(weight<=0) throw new IllegalArgumentException("Weight can't be 0 or less!");

        return new Cargo(name,weight,CargoStatus.PREPARED);
    }
}

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
    private int id;
    private String name;
    private int weight;
    private CargoStatus status;

    public NewCargoDTO(Cargo c){
        id=c.getId();
        name=c.getName();
        weight=c.getWeight();
        status=c.getStatus();
    }

    public Cargo toCargo(){
        if(name==null||name.isEmpty()) throw new IllegalArgumentException("Cargo name is empty!");
        if(weight<=0) throw new IllegalArgumentException("Weight can't be 0 or less!");

        Cargo cargo=new Cargo(name,weight,status);
        cargo.setId(id);
        return cargo;
    }
}

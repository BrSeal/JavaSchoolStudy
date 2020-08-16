package main.core.cargo.DTO;

import main.model.logistic.Cargo;
import main.model.logistic.CargoStatus;

public class UpdateStatusCargoCTO implements CargoDTO{

    private int id;
    private CargoStatus status;
    @Override
    public Cargo toCargo() {
        Cargo cargo=new Cargo(null,0,status);
        cargo.setId(id);
        return cargo;
    }
}

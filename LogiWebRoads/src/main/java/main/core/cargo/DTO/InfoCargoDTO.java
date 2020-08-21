package main.core.cargo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.globalLogic.exceptionHandling.exceptions.DtoConvertForbiddenException;
import main.model.logistic.Cargo;
import main.model.logistic.CargoStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoCargoDTO implements CargoDTO{
    private int id;
    private int weight;
    private String name;
    private CargoStatus status;
    private int cityIdFrom;
    private int cityIdTo;
    private int orderId;

    public InfoCargoDTO(Cargo cargo,int from,int to, int orderId){
        id=cargo.getId();
        weight=cargo.getWeight();
        name=cargo.getName();
        status=cargo.getStatus();
        cityIdFrom=from;
        cityIdTo=to;
        this.orderId=orderId;
    }

    @Override
    public Cargo toCargo() {
     throw new DtoConvertForbiddenException();
    }
}

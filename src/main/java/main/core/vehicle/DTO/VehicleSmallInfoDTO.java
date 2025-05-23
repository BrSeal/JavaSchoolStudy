package main.core.vehicle.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.vehicle.entity.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSmallInfoDTO {
    private int id;
    private String regNumber;
    private boolean ok;
    private int currentCityId;
    private int currentOrderId;

    public VehicleSmallInfoDTO(Vehicle v) {
        id = v.getId();
        regNumber = v.getRegNumber().toUpperCase();
        ok = v.isOk();
        currentCityId = v.getCurrentCity().getId();
        currentOrderId=v.getCurrentOrder()==null?0:v.getCurrentOrder().getId();
    }
}

package main.core.vehicle.DTO;

import main.core.vehicle.entity.Vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

package main.core.vehicle.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.entity.Order;
import main.core.vehicle.entity.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFullInfoDTO {


    public VehicleFullInfoDTO(Vehicle v) {
        id = v.getId();
        regNumber = v.getRegNumber().toUpperCase();
        dutySize = v.getDutySize();
        capacity = v.getCapacity();
        ok = v.isOk();
        currentCityId = v.getCurrentCity().getId();
        currentOrder = v.getCurrentOrder() == null ? 0 : v.getCurrentOrder().getId();
    }

    private int id;
    private String regNumber;
    private int dutySize;
    private int capacity;
    private boolean ok;
    private int currentCityId;
    private int currentOrder;
}

package main.core.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.City;
import main.model.logistic.Vehicle;
import main.model.logistic.orderAndWaypoint.Order;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    public VehicleDTO(Vehicle v) {
        id = v.getId();
        regNumber = v.getRegNumber();
        dutySize = v.getDutySize();
        capacity = v.getCapacity();
        isOk = v.isOk();
        currentCityId = v.getCurrentCity().getId();
        currentCityName = v.getCurrentCity().getName();
        currentOrder = v.getCurrentOrder() == null ? 0 : v.getCurrentOrder().getId();
    }

    private int id;
    private String regNumber;
    private int dutySize;
    private int capacity;
    private boolean isOk;
    private int currentCityId;
    private String currentCityName;
    private int currentOrder;

    public Vehicle toVehicle() {
        City city = new City();
        city.setId(currentCityId);
        Order order;

        if (currentOrder == 0) order = null;
        else {
            order = new Order();
            order.setId(currentOrder);
        }

        Vehicle vehicle = new Vehicle(regNumber, dutySize, capacity, isOk, city, order);
        vehicle.setId(id);

        return vehicle;
    }
}

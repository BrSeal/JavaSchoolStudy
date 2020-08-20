package main.core.vehicle.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.City;
import main.model.logistic.Order;
import main.model.logistic.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private static final int MIN_CAPACITY = 100;
    private static final String LOW_CAPACITY_ERR = "Capacity must be grater than "+MIN_CAPACITY+"!";
    private static final String REG_NUMBER_FORMAT_ERR = "Registration number format error!";
    public VehicleDTO(Vehicle v) {
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

    public Vehicle toVehicle() {
        if(capacity<MIN_CAPACITY) throw new IllegalArgumentException(LOW_CAPACITY_ERR);
        if(!regNumber.matches("^[A-Z]\\d{3}[A-Z]{2}$")) throw new IllegalArgumentException(REG_NUMBER_FORMAT_ERR);


        City city = new City();
        city.setId(currentCityId);
        Order order;

        if (currentOrder == 0) order = null;
        else {
            order = new Order();
            order.setId(currentOrder);
        }

        Vehicle vehicle = new Vehicle(regNumber, dutySize, capacity, ok, city, order);
        vehicle.setId(id);

        return vehicle;
    }
}

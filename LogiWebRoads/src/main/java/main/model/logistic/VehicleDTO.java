package main.model.logistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private int id;
    private String regNumber;
    private int dutySize;
    private int capacity;
    private boolean isOk;
    private int currentCity;
    private int currentOrder;

    public Vehicle toVehicle() {
        City city = new City();
        city.setId(currentCity);
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

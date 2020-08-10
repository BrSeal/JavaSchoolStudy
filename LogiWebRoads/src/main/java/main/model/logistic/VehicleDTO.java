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

    public Vehicle toVehicle(){
        City city=new City();
        city.setId(currentCity);

        Vehicle vehicle=new Vehicle(regNumber,dutySize,capacity,isOk,city);
        vehicle.setId(id);

        return vehicle;
    }
}

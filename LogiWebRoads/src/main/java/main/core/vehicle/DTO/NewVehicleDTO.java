package main.core.vehicle.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import main.core.cityAndRoads.cities.entity.City;
import main.core.vehicle.entity.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewVehicleDTO {

    private String regNumber;
    private int dutySize;
    private int capacity;
    private int currentCityId;

    public Vehicle toVehicle() {

        City city = new City();
        city.setId(currentCityId);

        Vehicle vehicle = new Vehicle(regNumber, dutySize, capacity, true, city, null);

        return vehicle;
    }
}

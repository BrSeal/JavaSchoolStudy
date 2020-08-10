package main.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.City;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    private int id;
    private String firstName;
    private String lastName;
    private int currentCity;
    private int hoursWorked;
    private DriverStatus status;

    public Driver toDriver(){
        City city=new City();
        city.setId(currentCity);

        Driver driver=new Driver(firstName,lastName,hoursWorked,status,city);
        driver.setId(id);

        return driver;
    }
}

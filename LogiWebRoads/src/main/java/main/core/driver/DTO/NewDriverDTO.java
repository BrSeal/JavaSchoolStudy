package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.City;
import main.model.users.Driver;
import main.model.users.DriverStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewDriverDTO implements DriverDTO{
    private String firstName;
    private String lastName;
    private int currentCityId;

    @Override
    public Driver toDriver() {
        City city=new City();
        city.setId(currentCityId);
        return new Driver(firstName,lastName,0, DriverStatus.ON_REST,city,null);
    }
}

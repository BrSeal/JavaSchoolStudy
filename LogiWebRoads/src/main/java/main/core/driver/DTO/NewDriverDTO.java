package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.Security.entity.User;
import main.core.cityAndRoads.cities.entity.City;
import main.core.driver.entity.Driver;
import main.core.driver.entity.DriverStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewDriverDTO {
    private String firstName;
    private String lastName;
    private int currentCityId;
    private String username;
    private String password;


    public Driver toDriver() {
        City city = new City();
        city.setId(currentCityId);

        User user = new User(username, password, true,null);

        return new Driver(firstName, lastName, 0, DriverStatus.ON_REST, city, null, user);
    }
}

package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.City;
import main.model.logistic.Order;
import main.model.users.Driver;
import main.model.users.DriverStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverInfoDTO implements DriverDTO{

    public DriverInfoDTO(Driver d) {
        id = d.getId();
        firstName = d.getFirstName();
        lastName = d.getLastName();
        currentCityId = d.getCurrentCity().getId();
        currentOrder = d.getCurrentOrder() == null ? 0 : d.getCurrentOrder().getId();
        hoursWorked = d.getHoursWorked();
        status = d.getStatus();

    }

    private int id;
    private String firstName;
    private String lastName;
    private int currentCityId;
    private int currentOrder;
    private int hoursWorked;
    private DriverStatus status;

    public Driver toDriver() {
        City city = new City();
        city.setId(currentCityId);
        Order order;

        if (currentOrder == 0) order = null;
        else {
            order = new Order();
            order.setId(currentOrder);
        }
        Driver driver = new Driver(firstName, lastName, hoursWorked, status, city, order);
        driver.setId(id);

        return driver;
    }
}
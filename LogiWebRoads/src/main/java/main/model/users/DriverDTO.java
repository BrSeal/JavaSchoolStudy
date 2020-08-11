package main.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.City;
import main.model.logistic.Order;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    private int id;
    private String firstName;
    private String lastName;
    private int currentCity;
    private int currentOrder;
    private int hoursWorked;
    private DriverStatus status;

    public Driver toDriver() {
        City city = new City();
        city.setId(currentCity);
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

package main.core.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.Order;
import main.model.users.Driver;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignDriversOrderDTO implements OrderDTO {
    private static final String NO_DRIVERS_ASSIGNED_ERR = "No drivers assigned! Please assign at least one driver!";
    private static final int NOTING = 0;
    private int id;
    private List<Integer> driverIds;

    @Override
    public Order toOrder() {
        if (driverIds == null || driverIds.isEmpty()) throw new IllegalArgumentException(NO_DRIVERS_ASSIGNED_ERR);

        Order order = new Order();
        order.setId(id);
        List<Driver> assignedDrivers = driverIds.stream()
                .map(d -> {
                    Driver driver = new Driver();
                    driver.setId(d);
                    return driver;
                })
                .collect(Collectors.toList());

        return order;
    }
}

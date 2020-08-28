package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.orderManagement.order.entity.Order;
import main.core.driver.entity.Driver;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignDriversOrderDTO implements OrderDTO {
    private int id;
    private List<Integer> driverIds;

    @Override
    public Order toOrder() {

        Order order = new Order();
        order.setId(id);
        List<Driver> assignedDrivers = driverIds.stream()
                .map(d -> {
                    Driver driver = new Driver();
                    driver.setId(d);
                    return driver;
                })
                .collect(Collectors.toList());

        order.setAssignedDrivers(assignedDrivers);

        return order;
    }
}

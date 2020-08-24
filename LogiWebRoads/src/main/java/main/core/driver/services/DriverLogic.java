package main.core.driver.services;

import lombok.Getter;
import lombok.Setter;
import main.core.order.services.OrderCheckProvider;
import main.core.order.services.OrderLogic;
import main.model.logistic.Order;
import main.model.users.Driver;
import main.model.users.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class DriverLogic {
    private static final String VEHICLE_NOT_ASSIGNED_ERR = "Vehicle is not assigned!";

    private DriverCheckProvider driverCheckProvider;
    private OrderCheckProvider orderCheckProvider;
    private OrderLogic orderLogic;

    @Autowired
    public DriverLogic(DriverCheckProvider driverCheckProvider,OrderCheckProvider orderCheckProvider,OrderLogic orderLogic) {
        this.driverCheckProvider = driverCheckProvider;
        this.orderCheckProvider = orderCheckProvider;
        this.orderLogic = orderLogic;
    }

    public int getHoursPerWorker(Order order) {
        if (!orderCheckProvider.isVehicleAssigned(order)) throw new IllegalArgumentException(VEHICLE_NOT_ASSIGNED_ERR);
        int dutySize = order.getAssignedVehicle().getDutySize();

        return (int) Math.ceil((double) orderLogic.calculateOrderWorkTimeFirstMonth(order) / dutySize);
    }

    public void updateStatus(Order order, Driver driver, DriverStatus status) {
        driverCheckProvider.canUpdateStatus(order, driver, status);

        driver.setStatus(status);
    }
}

package main.core.driver.services;

import main.model.logistic.Order;
import main.model.users.Driver;
import main.model.users.DriverStatus;

import static main.core.driver.services.DriverCheckProvider.canStatusBeUpdated;
import static main.core.order.services.OrderCheckProvider.isVehicleAssigned;
import static main.core.order.services.OrderLogic.calculateOrderWorkTimeFirstMonth;

public class DriverLogic {
    private static final String VEHICLE_NOT_ASSIGNED_ERR = "Vehicle is not assigned!";
    public static int getHoursPerWorker(Order order) {
        if (!isVehicleAssigned(order)) throw new IllegalArgumentException(VEHICLE_NOT_ASSIGNED_ERR);
        int dutySize = order.getAssignedVehicle().getDutySize();

        return  (int) Math.ceil((double) calculateOrderWorkTimeFirstMonth(order) / dutySize);
    }

    public static void updateStatus(Order order, Driver driver, DriverStatus status){
        canStatusBeUpdated(order,driver,status);

        driver.setStatus(status);
    }
}

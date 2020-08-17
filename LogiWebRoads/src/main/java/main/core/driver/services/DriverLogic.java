package main.core.driver.services;

import main.model.logistic.Order;

import static main.core.order.services.OrderCalculator.calculateOrderWorkTimeFirstMonth;
import static main.core.order.services.OrderUpdateChecker.isVehicleAssigned;

public class DriverLogic {
    private static final String VEHICLE_NOT_ASSIGNED_ERR = "Vehicle is not assigned!";
    public static int getHoursPerWorker(Order order) {
        if (!isVehicleAssigned(order)) throw new IllegalArgumentException(VEHICLE_NOT_ASSIGNED_ERR);
        int dutySize = order.getAssignedVehicle().getDutySize();
        return  (int) Math.ceil((double) calculateOrderWorkTimeFirstMonth(order) / dutySize);
    }
}

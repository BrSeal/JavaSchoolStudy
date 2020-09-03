package main.core.orderManagement.order.services;

import main.core.cityAndRoads.cities.entity.City;
import main.core.driver.entity.Driver;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.vehicle.entity.Vehicle;
import main.global.exceptionHandling.exceptions.UpdateFailException;

import java.util.List;

public class OrderCheckProvider {

    private static final String ORDER_ALREADY_COMPLETED_ERR = "Order #%d is already completed!";
    private static final String DRIVER_ALREADY_IN_ORDER_ERR = "Driver #%d is already assigned to order #%d!";
    private static final String DRIVER_IN_OTHER_CITY_ERR = "Driver #%d is in %s but vehicle is in %s!";
    private static final String NO_DRIVERS_ASSIGNED_ERR = "No drivers assigned! Please assign at least one driver!";
    private static final String ALREADY_ASSIGNED_ERR = "Vehicle #%d is already assigned to order #%d!";
    private static final String NOT_ENOUGH_HOURS_ERR = "Driver #%d would be overworked!";
    private static final String BROKEN_VEHICLE_ERR = "Vehicle #%d is broken and can't be used!";
    private static final String ORDER_STARTED_ERR = "Order #%d is already in work!";
    private static final String LOW_CAPACITY_ERR = "Capacity of the vehicle #%d is too low! Need at least %d.";
    private static final String NO_VEHICLE_ERR = "Order #%d has no assigned vehicle! Please assign vehicle first!";
    private static final String DUTY_SIZE_ERR = "You selected too many drivens! Assigned vehicle can handle only %d person/s";

    private static final int WORK_HOURS_PER_MONTH = 176;

    public boolean isVehicleAssigned(Order order) {
        return order.getAssignedVehicle() != null;
    }

    public void driverAssignmentCheck(Order order, List<Driver> drivers, int hoursPerDriver) {
        isOrderCompleted(order);

        if (drivers == null || drivers.isEmpty()) throw new UpdateFailException(NO_DRIVERS_ASSIGNED_ERR);

        if (!isVehicleAssigned(order)) throw new UpdateFailException(String.format(NO_VEHICLE_ERR, order.getId()));

        Vehicle vehicle = order.getAssignedVehicle();

        if (drivers.size() > vehicle.getDutySize()) {
            String errMsg = String.format(DUTY_SIZE_ERR, vehicle.getDutySize());
            throw new UpdateFailException(errMsg);
        }

        City city = vehicle.getCurrentCity();

        for (Driver d : drivers) {
            if (d.getCurrentOrder() != null) {
                String errMsg = String.format(DRIVER_ALREADY_IN_ORDER_ERR, d.getId(), d.getCurrentOrder().getId());
                throw new UpdateFailException(errMsg);
            }
            if (d.getCurrentCity() != city) {
                String errMsg = String.format(DRIVER_IN_OTHER_CITY_ERR, d.getId(), d.getCurrentCity().getName(), city.getName());
                throw new UpdateFailException(errMsg);
            }

            if (WORK_HOURS_PER_MONTH - d.getHoursWorked() < hoursPerDriver / vehicle.getDutySize()) {
                String errMsg = String.format(NOT_ENOUGH_HOURS_ERR, d.getId());
                throw new UpdateFailException(errMsg);
            }
        }

    }

    public void vehicleAssignmentCheck(Order order, Vehicle vehicle, int maxLoad) {
        isOrderCompleted(order);

        int capacity = vehicle.getCapacity();

        if (order.getAssignedVehicle() != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
            String errMsg = String.format(ORDER_STARTED_ERR, order.getId());
            throw new UpdateFailException(errMsg);
        }

        if (!vehicle.isOk()) {
            String errMsg = String.format(BROKEN_VEHICLE_ERR, vehicle.getId());
            throw new UpdateFailException(errMsg);
        }

        if (vehicle.getCurrentOrder() != null) {
            String errMsg = String.format(ALREADY_ASSIGNED_ERR, vehicle.getId(), vehicle.getCurrentOrder().getId());
            throw new UpdateFailException(errMsg);
        }

        if (capacity < maxLoad) {
            throw new UpdateFailException(String.format(LOW_CAPACITY_ERR, vehicle.getId(), maxLoad));
        }
    }

    public void isOrderCompleted(Order order) {
        String errMsg = String.format(ORDER_ALREADY_COMPLETED_ERR, order.getId());
        if (order.getStatus() == OrderStatus.COMPLETED) throw new UpdateFailException(errMsg);
    }
}
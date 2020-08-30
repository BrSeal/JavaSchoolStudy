package main.core.orderManagement.order.services;

import main.core.cargo.entity.CargoStatus;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.vehicle.entity.Vehicle;
import main.core.driver.entity.Driver;

import java.util.List;

public class OrderCheckProvider {

    private static final String LOW_CAPACITY_ERR = "Capacity of the vehicle #%d is too low! Need at least %d.";
    private static final String NOT_ENOUGH_HOURS_ERR = "Driver #%d would be overworked!";
    private static final String ALREADY_ASSIGNED_ERR = "Vehicle #%d is already assigned to order #%d!";
    private static final String DRIVER_IN_OTHER_CITY_ERR = "Driver #%d is in %s but vehicle is in %s!";
    private static final String DRIVER_ALREADY_IN_ORDER_ERR = "Driver #%d is already assigned to order #%d!";
    private static final String NO_VEHICLE_ERR = "Order #%d has no assigned vehicle! Please assign vehicle first!";
    private static final String BROKEN_VEHICLE_ERR = "Vehicle #%d is broken and can't be used!";
    private static final String ORDER_STARTED_ERR = "Order #%d is already in work!";
    private static final String DUTY_SIZE_ERR = "You selected too many drivens! Assigned vehicle can handle only %d person/s";
    private static final String ORDER_ALREADY_COMPLETED_ERR = "Order #%d is already completed!";
    private static final String NO_DRIVERS_ASSIGNED_ERR = "No drivers assigned! Please assign at least one driver!";
    private static final String NOT_FOUND = "No drivers assigned! Please assign at least one driver!";


    private static final int WORK_HOURS_PER_MONTH = 176;

    public void exists(Order o) {
        if (o == null) throw new IllegalArgumentException(NOT_FOUND);
    }

    public boolean isVehicleAssigned(Order order) {
        return order.getAssignedVehicle() != null;
    }

    public void driverAssignmentCheck(Order order, List<Driver> drivers, int hoursPerDriver) {

        if (!isVehicleAssigned(order)) throw new IllegalArgumentException(String.format(NO_VEHICLE_ERR, order.getId()));

        Vehicle vehicle = order.getAssignedVehicle();

        if (drivers.size() > vehicle.getDutySize()) {
            String errMsg = String.format(DUTY_SIZE_ERR, vehicle.getDutySize());
            throw new IllegalArgumentException(errMsg);
        }

        City city = vehicle.getCurrentCity();

        for (Driver d : drivers) {
            if (d.getCurrentOrder() != null) {
                String errMsg = String.format(DRIVER_ALREADY_IN_ORDER_ERR, d.getId(), d.getCurrentOrder().getId());
                throw new IllegalArgumentException(errMsg);
            }
            if (d.getCurrentCity() != city) {
                String errMsg = String.format(DRIVER_IN_OTHER_CITY_ERR, d.getId(), d.getCurrentCity().getName(), city.getName());
                throw new IllegalArgumentException(errMsg);
            }

            if (WORK_HOURS_PER_MONTH - d.getHoursWorked() < hoursPerDriver / vehicle.getDutySize()) {
                String errMsg = String.format(NOT_ENOUGH_HOURS_ERR, d.getId());
                throw new IllegalArgumentException(errMsg);
            }
        }

    }

    public void vehicleAssignmentCheck(Order order, Vehicle vehicle, int maxLoad) {
        int capacity = vehicle.getCapacity();
        boolean orderStarted = order.getWaypoints().get(0).getCargo().getStatus() == CargoStatus.TRANSPORTING;

        if (order.getAssignedVehicle() != null && orderStarted) {
            String errMsg = String.format(ORDER_STARTED_ERR, order.getId());
            throw new IllegalArgumentException(errMsg);
        }

        if (!vehicle.isOk()) {
            String errMsg = String.format(BROKEN_VEHICLE_ERR, vehicle.getId());
            throw new IllegalArgumentException(errMsg);
        }

        if (vehicle.getCurrentOrder() != null) {
            String errMsg = String.format(ALREADY_ASSIGNED_ERR, vehicle.getId(), vehicle.getCurrentOrder().getId());
            throw new IllegalArgumentException(errMsg);
        }

        if (capacity < maxLoad) throw
                new IllegalArgumentException(String.format(LOW_CAPACITY_ERR, vehicle.getId(), maxLoad));
    }

    public void isOrderCompleted(Order order) {
        if (order.getStatus()== OrderStatus.COMPLETED)
            throw new IllegalArgumentException(String.format(ORDER_ALREADY_COMPLETED_ERR, order.getId()));
    }

    public void isDriverListEmptyOrNull(List<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) throw new IllegalArgumentException(NO_DRIVERS_ASSIGNED_ERR);
    }
}

package main.core.driver.services;

import main.model.logistic.Order;
import main.model.users.Driver;
import main.model.users.DriverStatus;

import static main.model.users.DriverStatus.ON_DUTY_DRIVING;
import static main.model.users.DriverStatus.ON_REST;

public class DriverCheckProvider {
    private static final String DELETE_ERR = "Deletion failed! Driver #%d is currently on order #%d!";
    private static final String ALREADY_DRIVING = "You can't be driving. Someone is already driving!";
    private static final String NOT_IN_ORDER_STATUS_CHANGE_ERR = "You can't change status being on rest!";
    private static final String CANT_LEAVE_ORDER = "You are on duty!";
    private static final String SAME_STATUS_ERR = "You tried to assign the same status!";

    public static void canBeDeleted(Driver d) {
        boolean canBeDeleted=  d.getCurrentOrder() == null && d.getStatus().equals(DriverStatus.ON_REST);

        if (!canBeDeleted) {
            int id = d.getId();
            int orderId = d.getCurrentOrder().getId();
            throw new IllegalArgumentException(String.format(DELETE_ERR, id, orderId));
        }
    }

    public static void canStatusBeUpdated(Order order, Driver driver, DriverStatus status){
        if(driver.getCurrentOrder()==null) throw new IllegalArgumentException(NOT_IN_ORDER_STATUS_CHANGE_ERR);

        if(driver.getStatus()==status) throw new IllegalArgumentException(SAME_STATUS_ERR);

       boolean isSomeoneDriving= order.getAssignedDrivers().stream()
                .anyMatch(dr->dr.getStatus()== ON_DUTY_DRIVING);

        if(isSomeoneDriving) throw new IllegalArgumentException(ALREADY_DRIVING);

        if(status==ON_REST) throw new IllegalArgumentException(CANT_LEAVE_ORDER);

    }

}

package main.core.driver.services;

import main.core.driver.DTO.DriverUpdateDTO;
import main.core.driver.entity.Driver;
import main.core.driver.entity.DriverStatus;
import main.core.orderManagement.order.entity.Order;
import main.global.exceptionHandling.exceptions.DeletionFailException;
import main.global.exceptionHandling.exceptions.UpdateFailException;

import static main.core.driver.entity.DriverStatus.ON_DUTY_DRIVING;
import static main.core.driver.entity.DriverStatus.ON_REST;

public class DriverCheckProvider {
    private static final int MAX_WORKING_HOURS_VALUE = 176;
    private static final int MIN_WORKING_HOURS_VALUE = 0;
    private static final int NOT_PRESENT = 0;

    private static final String DELETE_ERR = "Deletion failed! Driver #%d is currently on order #%d! Driver can be deleted only after finishing his current task!";
    private static final String ALREADY_DRIVING = "You can't be driving. Someone is already driving!";
    private static final String NOT_IN_ORDER_STATUS_CHANGE_ERR = "You can't change status being on rest!";
    private static final String CANT_LEAVE_ORDER = "You are on duty!";
    private static final String SAME_STATUS_ERR = "You tried to assign the same status!";
    private static final String UPDATE_FAIL_EMPTY_NAME = "Driver #%d cant be updated! First name and last name can't be empty!";
    private static final String UPDATE_FAIL_WRONG_HOURS_WORKED = "Driver #%d cant be updated! Working hours must be greater than 0 and less or equal to 176!";
    private static final String UPDATE_FAIL_ORDER = "Driver #%d cant be updated! You can reassign driver only from order menu!";
    private static final String UPDATE_FAIL_CITY_CHANGE_FORBIDDEN = "Driver #%d cant be updated! You can't change current location while driver is on order. It changes automatically!";
    private static final String HOURS_UPDATE_FAIL_ON_ORDER = "Failed to change working hours on Driver #%d because he is currently working on order #%d!";

    public void canBeDeleted(Driver driver) {
        Order currentOrder=driver.getCurrentOrder();

        if (currentOrder!=null) {
            int id = driver.getId();
            int orderId = currentOrder.getId();
            throw new DeletionFailException(String.format(DELETE_ERR, id, orderId));
        }
    }

    public void canBeUpdated(Driver driver, DriverUpdateDTO updates) {
        String newFirstName = updates.getFirstName();
        String newLastName = updates.getLastName();
        Order currentOrder=driver.getCurrentOrder();
        int newCity = updates.getCurrentCityId();
        int newHoursWorked = updates.getHoursWorked();
        int newOrder = updates.getCurrentOrder();

        int currentOrderId = currentOrder==null ? NOT_PRESENT : currentOrder.getId();

        if (newFirstName == null || newLastName == null || newFirstName.isEmpty() || newLastName.isEmpty()) {
            String errMsg = String.format(UPDATE_FAIL_EMPTY_NAME, driver.getId());
            throw new UpdateFailException(errMsg);
        }

        if(newHoursWorked!=driver.getHoursWorked()&&currentOrder!=null){
            String errMsg= String.format(HOURS_UPDATE_FAIL_ON_ORDER,driver.getId(),currentOrder.getId());
            throw new UpdateFailException(errMsg);
        }

        if (newHoursWorked < MIN_WORKING_HOURS_VALUE || newHoursWorked > MAX_WORKING_HOURS_VALUE) {
            String errMsg = String.format(UPDATE_FAIL_WRONG_HOURS_WORKED, driver.getId());
            throw new UpdateFailException(errMsg);
        }

        if (newOrder != 0 && newOrder != currentOrderId) {
            String errMsg = String.format(UPDATE_FAIL_ORDER, driver.getId());
            throw new UpdateFailException(errMsg);
        }

        if (newCity != 0 && driver.getCurrentCity().getId() != newCity && currentOrder != null) {
            String errMsg = String.format(UPDATE_FAIL_CITY_CHANGE_FORBIDDEN, driver.getId());
            throw new UpdateFailException(errMsg);
        }
    }

    public void canUpdateStatus(Order order, Driver driver, DriverStatus status) {

        if (driver.getCurrentOrder() == null) throw new IllegalArgumentException(NOT_IN_ORDER_STATUS_CHANGE_ERR);

        if (driver.getStatus() == status) throw new IllegalArgumentException(SAME_STATUS_ERR);

        boolean isSomeoneDriving = order.getAssignedDrivers().stream()
                .anyMatch(dr -> dr.getStatus() == ON_DUTY_DRIVING&&!dr.equals(driver));

        if (isSomeoneDriving) throw new IllegalArgumentException(ALREADY_DRIVING);

        if (status == ON_REST) throw new IllegalArgumentException(CANT_LEAVE_ORDER);

    }

}

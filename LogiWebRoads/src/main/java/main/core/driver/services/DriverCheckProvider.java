package main.core.driver.services;

import main.core.cityAndRoads.cities.entity.City;
import main.core.driver.DTO.DriverUpdateDTO;
import main.core.driver.entity.Driver;
import main.core.driver.entity.DriverStatus;
import main.core.orderManagement.order.entity.Order;
import main.global.exceptionHandling.exceptions.DeletionFailException;
import main.global.exceptionHandling.exceptions.UpdateFailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static main.core.driver.entity.DriverStatus.*;

public class DriverCheckProvider {
    private static final int MIN_WORKING_HOURS_VALUE = 0;
    private static final int MAX_WORK_HOURS_PER_MONTH = 176;
    private static final int NOT_PRESENT = 0;

    private static final String NOT_DRIVING_ERR = "You must set status ON_DUTY_DRIVING to start load or unload cargo!";
    private static final String DELETE_ERR = "Deletion failed! Driver #%d is currently on order #%d! Driver can be deleted only after finishing his current task!";
    private static final String AUTH_ERR = "You are not aloud to provide this action!";
    private static final String ALREADY_DRIVING = "You can't be driving. Someone is already driving!";
    private static final String NOT_IN_ORDER_STATUS_CHANGE_ERR = "You can't change status being on rest!";
    private static final String CANT_LEAVE_ORDER = "You are on duty!";
    private static final String SAME_STATUS_ERR = "You tried to assign the same status!";
    private static final String EMPTY_NAME = "Driver #%d cant be updated! First name and last name can't be empty!";
    private static final String WRONG_HOURS_WORKED = "Driver #%d cant be updated! Working hours must be greater than 0 and less or equal to 176!";
    private static final String ORDER = "Driver #%d cant be updated! You can reassign driver only from order menu!";
    private static final String CITY_CHANGE_FORBIDDEN = "Driver #%d cant be updated! You can't change current location while driver is on order. It changes automatically!";
    private static final String HOURS_ON_ORDER = "Failed to change working hours on Driver #%d because he is currently working on order #%d!";
    private static final String NOT_ALL_DRIVERS_ON_DUTY = "Not all drivers on duty!";

    private static final String DRIVER_ALREADY_IN_ORDER_ERR = "Driver #%d is already assigned to order #%d!";
    private static final String DRIVER_IN_OTHER_CITY_ERR = "Driver #%d is in %s but vehicle is in %s!";
    private static final String NO_DRIVERS_ASSIGNED_ERR = "No drivers assigned! Please assign at least one driver!";
    private static final String NOT_ENOUGH_HOURS_ERR = "Driver #%d would be overworked!";
    private static final String DUTY_SIZE_ERR = "You selected too many drivers! Assigned vehicle can handle only %d person/s";
    private static final String NOT_ENOUGH_DRIVERS_ERR = "You need to assign more driver on the order #%d!";


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
            String errMsg = String.format(EMPTY_NAME, driver.getId());
            throw new UpdateFailException(errMsg);
        }

        if(newHoursWorked!=driver.getHoursWorked()&&currentOrder!=null){
            String errMsg= String.format(HOURS_ON_ORDER,driver.getId(),currentOrder.getId());
            throw new UpdateFailException(errMsg);
        }

        if (newHoursWorked < MIN_WORKING_HOURS_VALUE || newHoursWorked > MAX_WORK_HOURS_PER_MONTH) {
            String errMsg = String.format(WRONG_HOURS_WORKED, driver.getId());
            throw new UpdateFailException(errMsg);
        }

        if (newOrder != 0 && newOrder != currentOrderId) {
            String errMsg = String.format(ORDER, driver.getId());
            throw new UpdateFailException(errMsg);
        }

        if (newCity != 0 && driver.getCurrentCity().getId() != newCity && currentOrder != null) {
            String errMsg = String.format(CITY_CHANGE_FORBIDDEN, driver.getId());
            throw new UpdateFailException(errMsg);
        }
    }

    public void canUpdateStatus(Order order, Driver driver, DriverStatus status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if(!driver.getUser().getUsername().equals(username)||!driver.getUser().isEnabled()) {
            throw new UpdateFailException(AUTH_ERR);
        }

        if (driver.getCurrentOrder() == null) {
            throw new UpdateFailException(NOT_IN_ORDER_STATUS_CHANGE_ERR);
        }

        if (driver.getStatus() == status){
            throw new UpdateFailException(SAME_STATUS_ERR);
        }

        boolean isSomeoneDriving = order.getAssignedDrivers().stream()
                .anyMatch(dr -> dr.getStatus() == ON_DUTY_DRIVING&&!dr.equals(driver));

        if (isSomeoneDriving) {
            throw new UpdateFailException(ALREADY_DRIVING);
        }

        if (status == ON_REST){
            throw new UpdateFailException(CANT_LEAVE_ORDER);
        }

    }

    public void canBeAssigned(List<Driver> drivers, int minDutySize, int currentVehicleDutySize, City city, int orderId, int hoursPerDriver){

        if (drivers == null || drivers.isEmpty()) throw new UpdateFailException(NO_DRIVERS_ASSIGNED_ERR);
        int driversCount=drivers.size();

        if(driversCount<minDutySize){
            String errMsg = String.format(NOT_ENOUGH_DRIVERS_ERR, orderId);
            throw new UpdateFailException(errMsg);
        }

        if ( driversCount> currentVehicleDutySize) {
            String errMsg = String.format(DUTY_SIZE_ERR, currentVehicleDutySize);
            throw new UpdateFailException(errMsg);
        }

        for (Driver d : drivers) {
            if (d.getCurrentOrder() != null) {
                String errMsg = String.format(DRIVER_ALREADY_IN_ORDER_ERR, d.getId(), d.getCurrentOrder().getId());
                throw new UpdateFailException(errMsg);
            }

            if (d.getCurrentCity() != city) {
                String errMsg = String.format(DRIVER_IN_OTHER_CITY_ERR, d.getId(), d.getCurrentCity().getName(), city.getName());
                throw new UpdateFailException(errMsg);
            }

            if (MAX_WORK_HOURS_PER_MONTH - d.getHoursWorked() < hoursPerDriver / currentVehicleDutySize) {
                String errMsg = String.format(NOT_ENOUGH_HOURS_ERR, d.getId());
                throw new UpdateFailException(errMsg);
            }
        }
    }

    public void canUpdateCargoStatus(List<Driver> drivers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Driver current=drivers.stream().filter(driver -> driver.getUser().getUsername().equals(username)&&driver.getUser().isEnabled()).findFirst().get();
        if(current==null) throw new UpdateFailException(AUTH_ERR);
        
        boolean isEveryoneOnDuty=drivers.stream().allMatch(d->d.getStatus()==ON_DUTY_DRIVING||d.getStatus()==ON_DUTY_REST);
        if(isEveryoneOnDuty) throw new UpdateFailException(NOT_ALL_DRIVERS_ON_DUTY);
        if (current.getStatus()!= ON_DUTY_DRIVING) throw new UpdateFailException(NOT_DRIVING_ERR);
    }
}

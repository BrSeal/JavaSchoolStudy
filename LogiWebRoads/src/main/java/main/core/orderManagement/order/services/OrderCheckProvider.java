package main.core.orderManagement.order.services;

import main.core.cityAndRoads.cities.entity.City;
import main.core.driver.entity.Driver;
import main.core.orderManagement.cargo.services.CargoCheckProvider;
import main.core.orderManagement.order.DTO.DeliveryObject;
import main.core.orderManagement.order.DTO.NewOrderDTO;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.vehicle.entity.Vehicle;
import main.global.exceptionHandling.exceptions.SaveFailedException;
import main.global.exceptionHandling.exceptions.UpdateFailException;
import org.springframework.beans.factory.annotation.Autowired;

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
    private static final String DUTY_SIZE_ERR = "You selected too many drivers! Assigned vehicle can handle only %d person/s";
    private static final String EMPTY_WAYPOINTS_LIST_ERR = "Can't save empty order!";
    private static final String CITY_DOES_NOT_EXIST = "City #%d does not exist in database!";
    private static final String LOW_DUTY_SIZE = "Vehicle #%d has too little duty size for order #%d!";
    private static final String NOT_ENOUGH_DRIVERS_ERR = "You need to assign more driver on order #%d!";
    private static final int WORK_HOURS_PER_MONTH = 176;

    private final CargoCheckProvider cargoCheckProvider;

    @Autowired
    public OrderCheckProvider(CargoCheckProvider cargoCheckProvider) {
        this.cargoCheckProvider = cargoCheckProvider;
    }

    public boolean isVehicleAssigned(Order order) {
        return order.getAssignedVehicle() != null;
    }

    public void driverAssignmentCheck(Order order, List<Driver> drivers, int hoursPerDriver, int minDutySize) {
        isOrderCompleted(order);

        if (drivers == null || drivers.isEmpty()) throw new UpdateFailException(NO_DRIVERS_ASSIGNED_ERR);

        int driversCount=drivers.size();

        if (!isVehicleAssigned(order)) throw new UpdateFailException(String.format(NO_VEHICLE_ERR, order.getId()));

        Vehicle vehicle = order.getAssignedVehicle();

        if(driversCount<minDutySize){
            String errMsg = String.format(NOT_ENOUGH_DRIVERS_ERR, order.getId());
            throw new UpdateFailException(errMsg);
        }

        if ( driversCount> vehicle.getDutySize()) {
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

    public void vehicleAssignmentCheck(Order order, Vehicle vehicle, int maxLoad, int minDutySize) {
        isOrderCompleted(order);

        int capacity = vehicle.getCapacity();

        if (order.getAssignedVehicle() != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
            String errMsg = String.format(ORDER_STARTED_ERR, order.getId());
            throw new UpdateFailException(errMsg);
        }

        if(minDutySize>vehicle.getDutySize()) {
            String errMsg=String.format(LOW_DUTY_SIZE, vehicle.getId(),order.getId());
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

    public  void validateNew(NewOrderDTO dto, List<Integer> cities){
        List<DeliveryObject> deliveryObjects=dto.getDeliveryObjects();

        if (deliveryObjects == null || deliveryObjects.isEmpty())
            throw new SaveFailedException(EMPTY_WAYPOINTS_LIST_ERR);

        deliveryObjects.forEach(obj -> {
            cargoCheckProvider.validateNew(obj.getCargo());
            cityIdExists(obj.getCityIdFrom(), cities);
            cityIdExists(obj.getCityIdTo(), cities);
        });
    }


    private void cityIdExists(int id,  List<Integer> cities){
        if(id<=0||!cities.contains(id)) {
            String errMsg=String.format(CITY_DOES_NOT_EXIST,id);
            throw new SaveFailedException(errMsg);
        }
    }
}

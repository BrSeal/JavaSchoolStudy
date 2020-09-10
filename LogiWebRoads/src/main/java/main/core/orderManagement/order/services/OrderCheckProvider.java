package main.core.orderManagement.order.services;

import main.core.cityAndRoads.cities.entity.City;
import main.core.driver.entity.Driver;
import main.core.driver.services.DriverCheckProvider;
import main.core.orderManagement.cargo.services.CargoCheckProvider;
import main.core.orderManagement.order.DTO.DeliveryObject;
import main.core.orderManagement.order.DTO.NewOrderDTO;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.vehicle.entity.Vehicle;
import main.core.vehicle.services.VehicleCheckProvider;
import main.global.exceptionHandling.exceptions.SaveFailedException;
import main.global.exceptionHandling.exceptions.UpdateFailException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderCheckProvider {

    private static final String ALREADY_COMPLETED_ERR = "Order #%d is already completed!";
    private static final String ALREADY_STARTED_ERR = "Order #%d is already in work!";
    private static final String NO_VEHICLE_ERR = "Order #%d has no assigned vehicle! Please assign vehicle first!";
    private static final String EMPTY_WAYPOINTS_LIST_ERR = "Can't save empty order!";
    private static final String CITY_DOES_NOT_EXIST = "City #%d does not exist in database!";

    private final CargoCheckProvider cargoCheckProvider;
    private final VehicleCheckProvider vehicleCheckProvider;
    private final DriverCheckProvider driverCheckProvider;
    private final OrderLogic orderLogic;

    @Autowired
    public OrderCheckProvider(CargoCheckProvider cargoCheckProvider, VehicleCheckProvider vehicleCheckProvider, DriverCheckProvider driverCheckProvider, OrderLogic orderLogic) {
        this.cargoCheckProvider = cargoCheckProvider;
        this.vehicleCheckProvider = vehicleCheckProvider;
        this.driverCheckProvider = driverCheckProvider;
        this.orderLogic = orderLogic;
    }

    public void driverAssignmentCheck(Order order, List<Driver> drivers) {
        int hoursPerDriver = orderLogic.calculateOrderWorkTimeFirstMonth(order);
        int minDutySize=orderLogic.calculateMinDutySize(order);
        Vehicle vehicle = order.getAssignedVehicle();

        isOrderCompleted(order);
        if (vehicle==null) throw new UpdateFailException(String.format(NO_VEHICLE_ERR, order.getId()));
        int dutySize=vehicle.getDutySize();
        City city = vehicle.getCurrentCity();

        driverCheckProvider.canBeAssigned(drivers,minDutySize,dutySize,city,order.getId(),hoursPerDriver);
    }

    public void vehicleAssignmentCheck(Order order, Vehicle vehicle) {
        int minDutySize=orderLogic.calculateMinDutySize(order);
        int maxLoad=orderLogic.calculateMaxLoad(order.getWaypoints());

        isOrderCompleted(order);
        if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            String errMsg = String.format(ALREADY_STARTED_ERR, order.getId());
            throw new UpdateFailException(errMsg);
        }
        vehicleCheckProvider.canBeAssigned(vehicle,minDutySize,maxLoad);
    }

    public void isOrderCompleted(Order order) {
        String errMsg = String.format(ALREADY_COMPLETED_ERR, order.getId());
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

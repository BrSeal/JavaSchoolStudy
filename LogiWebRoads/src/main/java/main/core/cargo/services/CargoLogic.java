package main.core.cargo.services;

import main.core.cargo.entity.Cargo;
import main.core.cargo.entity.CargoStatus;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.vehicle.entity.Vehicle;
import main.core.driver.entity.Driver;
import main.global.exceptionHandling.NullChecker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static main.core.cargo.entity.CargoStatus.DELIVERED;
import static main.core.cargo.entity.CargoStatus.TRANSPORTING;
import static main.core.orderManagement.waypoint.entity.WaypointType.LOAD;
import static main.core.orderManagement.waypoint.entity.WaypointType.UNLOAD;
import static main.core.driver.entity.DriverStatus.ON_DUTY_DRIVING;
import static main.core.driver.entity.DriverStatus.ON_REST;

public class CargoLogic {
    private static final String WAYPOINT_LOAD_NOT_FOUND="Load waypoint not found!";
    private static final String WAYPOINT_UNLOAD_NOT_FOUND="Unoad waypoint not found!";

    private final CargoCheckProvider cargoCheckProvider;
    private final OrderCheckProvider orderCheckProvider;
    private final NullChecker nullChecker;

    @Autowired
    public CargoLogic(CargoCheckProvider cargoCheckProvider, OrderCheckProvider orderCheckProvider,NullChecker nullChecker) {
        this.cargoCheckProvider = cargoCheckProvider;
        this.orderCheckProvider = orderCheckProvider;
        this.nullChecker = nullChecker;
    }

    public void updateStatusLogic(Cargo cargo, Cargo dto, Order order) {
        nullChecker.throwNotFoundIfNull(cargo,Cargo.class,dto.getId());

        CargoStatus cargoStatus = cargo.getStatus();
        CargoStatus dtoStatus = dto.getStatus();

        List<Driver> drivers = order.getAssignedDrivers();

        cargoCheckProvider.canBeUpdated(drivers, cargoStatus, dtoStatus);

        cargo.setStatus(dtoStatus);
        if (dtoStatus == TRANSPORTING) transportingStatusUpdateLogic(order, cargo);
        else if (dtoStatus == DELIVERED) deliveredStatusUpdateLogic(order, cargo);

    }

    private void transportingStatusUpdateLogic(Order order, Cargo cargo) {
        Waypoint waypoint = order.getWaypoints().stream()
                .filter(w -> w.getCargo() == cargo && w.getType() == LOAD && !w.isDone())
                .findFirst()
                .get();

        nullChecker.throwNotFoundIfNull(waypoint,WAYPOINT_LOAD_NOT_FOUND);

        waypoint.setDone(true);

        order.setStatus(OrderStatus.IN_PROGRESS);

        setCurrentCity(order.getAssignedDrivers(), order.getAssignedVehicle(), waypoint.getCity());
        incrementDriversWorkHours(order.getAssignedDrivers(), waypoint.getPathLength());
    }

    private void deliveredStatusUpdateLogic(Order order, Cargo cargo) {
        List<Waypoint> waypoints = order.getWaypoints();
        Waypoint waypoint = waypoints.stream()
                .filter(w -> w.getCargo() == cargo && w.getType() == UNLOAD && !w.isDone())
                .findFirst()
                .get();

        nullChecker.throwNotFoundIfNull(waypoint,WAYPOINT_UNLOAD_NOT_FOUND);

        waypoint.setDone(true);
        setCurrentCity(order.getAssignedDrivers(), order.getAssignedVehicle(), waypoint.getCity());
        if (waypoint.getPathIndex() == waypoints.size()) ifWasLastWaypoint(order);
    }

    private void ifWasLastWaypoint(Order order) {

        orderCheckProvider.isOrderCompleted(order);

        order.setStatus(OrderStatus.COMPLETED);
        order.getAssignedDrivers().forEach(d -> {
            d.setStatus(ON_REST);
            d.setCurrentOrder(null);
        });
        order.getAssignedVehicle().setCurrentOrder(null);
    }

    private void setCurrentCity(List<Driver> drivers, Vehicle vehicle, City city) {
        drivers.forEach(d -> d.setCurrentCity(city));
        vehicle.setCurrentCity(city);
    }

    private void incrementDriversWorkHours(List<Driver> drivers, int amount) {
        drivers.forEach(d -> {
            if (d.getStatus() == ON_DUTY_DRIVING) {
                int updatedValue = d.getHoursWorked() + amount;
                d.setHoursWorked(updatedValue);
            }
        });
    }
}
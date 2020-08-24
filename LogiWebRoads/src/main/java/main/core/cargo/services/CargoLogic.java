package main.core.cargo.services;

import main.core.order.services.OrderCheckProvider;
import main.model.logistic.*;
import main.model.users.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static main.model.logistic.CargoStatus.DELIVERED;
import static main.model.logistic.CargoStatus.TRANSPORTING;
import static main.model.logistic.WaypointType.LOAD;
import static main.model.logistic.WaypointType.UNLOAD;
import static main.model.users.DriverStatus.ON_DUTY_DRIVING;
import static main.model.users.DriverStatus.ON_REST;

public class CargoLogic {


    CargoCheckProvider cargoCheckProvider;
    OrderCheckProvider orderCheckProvider;

    @Autowired
    public CargoLogic(CargoCheckProvider cargoCheckProvider, OrderCheckProvider orderCheckProvider) {
        this.cargoCheckProvider = cargoCheckProvider;
        this.orderCheckProvider = orderCheckProvider;
    }

    public void updateStatusLogic(Cargo cargo, Cargo dto, Order order) {
        cargoCheckProvider.exists(cargo);

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

        waypoint.setDone(true);

        setCurrentCity(order.getAssignedDrivers(), order.getAssignedVehicle(), waypoint.getCity());
        incrementDriversWorkHours(order.getAssignedDrivers(), waypoint.getPathLength());
    }

    private void deliveredStatusUpdateLogic(Order order, Cargo cargo) {
        List<Waypoint> waypoints = order.getWaypoints();
        Waypoint waypoint = waypoints.stream()
                .filter(w -> w.getCargo() == cargo && w.getType() == UNLOAD && !w.isDone())
                .findFirst()
                .get();
        waypoint.setDone(true);
        setCurrentCity(order.getAssignedDrivers(), order.getAssignedVehicle(), waypoint.getCity());
        if (waypoint.getPathIndex() == waypoints.size()) ifWasLastWaypoint(order);
    }

    private void ifWasLastWaypoint(Order order) {

        orderCheckProvider.isOrderCompleted(order);

        order.setCompleted(true);
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
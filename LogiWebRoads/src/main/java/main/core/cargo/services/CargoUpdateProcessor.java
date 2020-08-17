package main.core.cargo.services;

import main.model.logistic.*;
import main.model.users.Driver;

import java.util.List;

import static main.model.logistic.CargoStatus.*;
import static main.model.logistic.WaypointType.*;
import static main.model.users.DriverStatus.*;

public class CargoUpdateProcessor {
    private static final String STATUS_UPDATE_ERR = "Cargo status can be changed in order PREPARED->TRANSPORTING->DELIVERED only!";
    private static final String STATUS_DID_NOT_CHANGED = "New status is the same as the previous one!";

    public static void updateStatusLogic(Cargo cargo, Cargo dto, Order order) {
        CargoStatus cargoStatus = cargo.getStatus();
        CargoStatus dtoStatus = dto.getStatus();

        updateCheck(cargoStatus, dtoStatus);

        cargo.setStatus(dtoStatus);
        if (dtoStatus == TRANSPORTING) transportingStatusUpdateLogic(order, cargo);
        else if (dtoStatus == DELIVERED) deliveredStatusUpdateLogic(order, cargo);

    }

    private static void updateCheck(CargoStatus cargoStatus, CargoStatus dtoStatus) {
        boolean isDowngradingOrJumping =
                (cargoStatus != PREPARED && dtoStatus == PREPARED)
                        ||
                        (cargoStatus == DELIVERED && dtoStatus != DELIVERED)
                        ||
                        (cargoStatus == PREPARED && dtoStatus == DELIVERED);


        if (isDowngradingOrJumping) throw new IllegalArgumentException(STATUS_UPDATE_ERR);

        if (cargoStatus == dtoStatus) throw new IllegalArgumentException(STATUS_DID_NOT_CHANGED);
    }

    private static void transportingStatusUpdateLogic(Order order, Cargo cargo) {
        Waypoint waypoint = order.getWaypoints().stream()
                .filter(w -> w.getCargo() == cargo && w.getType() == LOAD)
                .findFirst()
                .get();

        waypoint.setDone(true);

        setCurrentCity(order.getAssignedDrivers(), order.getAssignedVehicle(), waypoint.getCity());
        incrementDriversWorkHours(order.getAssignedDrivers(), waypoint.getPathLength());
    }

    private static void deliveredStatusUpdateLogic(Order order, Cargo cargo) {
        List<Waypoint> waypoints = order.getWaypoints();
        Waypoint waypoint = waypoints.stream()
                .filter(w -> w.getCargo() == cargo && w.getType() == UNLOAD)
                .findFirst()
                .get();
        waypoint.setDone(true);
        setCurrentCity(order.getAssignedDrivers(), order.getAssignedVehicle(), waypoint.getCity());
        if (waypoint.getPathIndex() == waypoints.size()) ifWasLastWaypoint(order);
        incrementDriversWorkHours(order.getAssignedDrivers(), waypoint.getPathLength());
    }

    private static void ifWasLastWaypoint(Order order) {
        order.setCompleted(true);
        order.getAssignedDrivers().forEach(d -> {
            d.setStatus(ON_REST);
            d.setCurrentOrder(null);
        });
        order.getAssignedVehicle().setCurrentOrder(null);
    }

    private static void setCurrentCity(List<Driver> drivers, Vehicle vehicle, City city) {
        drivers.forEach(d -> d.setCurrentCity(city));
        vehicle.setCurrentCity(city);
    }

    private static void incrementDriversWorkHours(List<Driver> drivers, int amount) {
        drivers.forEach(d -> {
            if (d.getStatus() == ON_DUTY_DRIVING) {
                int updatedValue = d.getHoursWorked() + amount;
                d.setHoursWorked(updatedValue);
            }
        });
    }
}
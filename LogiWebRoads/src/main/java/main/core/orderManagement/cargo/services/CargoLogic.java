package main.core.orderManagement.cargo.services;

import main.core.cityAndRoads.cities.entity.City;
import main.core.driver.entity.Driver;
import main.core.orderManagement.cargo.DTO.UpdateStatusCargoDTO;
import main.core.orderManagement.cargo.entity.Cargo;
import main.core.orderManagement.cargo.entity.CargoStatus;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.vehicle.entity.Vehicle;
import main.global.board.BoardInfo;
import main.global.exceptionHandling.NullChecker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static main.core.driver.entity.DriverStatus.ON_DUTY_DRIVING;
import static main.core.driver.entity.DriverStatus.ON_REST;
import static main.core.orderManagement.cargo.entity.CargoStatus.DELIVERED;
import static main.core.orderManagement.cargo.entity.CargoStatus.TRANSPORTING;
import static main.core.orderManagement.waypoint.entity.WaypointType.LOAD;
import static main.core.orderManagement.waypoint.entity.WaypointType.UNLOAD;

public class CargoLogic {
    private static final String WAYPOINT_LOAD_NOT_FOUND="Loading waypoint for cargo %s not found!";
    private static final String WAYPOINT_UNLOAD_NOT_FOUND="Unloading waypoint for cargo %s not found!";

    private final CargoCheckProvider cargoCheckProvider;
    private final NullChecker nullChecker;
    private final BoardInfo boardInfo;

    @Autowired
    public CargoLogic(CargoCheckProvider cargoCheckProvider,NullChecker nullChecker,BoardInfo boardInfo) {
        this.cargoCheckProvider = cargoCheckProvider;
        this.nullChecker = nullChecker;
        this.boardInfo=boardInfo;
    }

    public void updateStatus(Cargo cargo, UpdateStatusCargoDTO dto, Order order) {
        nullChecker.throwNotFoundIfNull(cargo,Cargo.class,dto.getId());

        CargoStatus cargoStatus = cargo.getStatus();
        CargoStatus dtoStatus = dto.getStatus();

        List<Driver> drivers = order.getAssignedDrivers();
        Vehicle vehicle=order.getAssignedVehicle();

        cargoCheckProvider.canBeUpdated(drivers, cargoStatus, dtoStatus, vehicle);

        cargo.setStatus(dtoStatus);
        if (dtoStatus == TRANSPORTING) transportingStatusUpdate(order, cargo);
        else if (dtoStatus == DELIVERED) deliveredStatusUpdate(order, cargo);

    }

    private void transportingStatusUpdate(Order order, Cargo cargo) {
        Waypoint waypoint = order.getWaypoints().stream()
                .filter(w -> w.getCargo() == cargo && w.getType() == LOAD && !w.isDone())
                .findFirst()
                .get();

        nullChecker.throwNotFoundIfNull(waypoint,String.format(WAYPOINT_LOAD_NOT_FOUND,cargo.getName()));

        waypoint.setDone(true);

        order.setStatus(OrderStatus.IN_PROGRESS);

        setCurrentCity(order.getAssignedDrivers(), order.getAssignedVehicle(), waypoint.getCity());
        incrementDriversWorkHours(order.getAssignedDrivers(), waypoint.getPathLength());
    }

    private void deliveredStatusUpdate(Order order, Cargo cargo) {
        List<Waypoint> waypoints = order.getWaypoints();
        Waypoint waypoint = waypoints.stream()
                .filter(w -> w.getCargo() == cargo && w.getType() == UNLOAD && !w.isDone())
                .findFirst()
                .get();

        nullChecker.throwNotFoundIfNull(waypoint,String.format(WAYPOINT_UNLOAD_NOT_FOUND,cargo.getName()));

        waypoint.setDone(true);
        setCurrentCity(order.getAssignedDrivers(), order.getAssignedVehicle(), waypoint.getCity());
        incrementDriversWorkHours(order.getAssignedDrivers(), waypoint.getPathLength());
        if (waypoint.getPathIndex() == waypoints.size()) ifWasLastWaypoint(order);
    }

    private void ifWasLastWaypoint(Order order) {
        order.setStatus(OrderStatus.COMPLETED);
        boardInfo.addOrUpdateOrderInfo(order);
        order.getAssignedDrivers().forEach(d -> {
            d.setStatus(ON_REST);
            boardInfo.decrementAssignedDrivers();

            d.setCurrentOrder(null);
        });
        order.getAssignedVehicle().setCurrentOrder(null);
        boardInfo.decrementVehiclesOnOrder();

        boardInfo.updateRemoteBoard();
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
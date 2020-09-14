package main;

import lombok.Getter;
import lombok.Setter;
import main.DTO.*;
import org.codehaus.jackson.map.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Named
@ApplicationScoped

public class InfoContainer implements Serializable {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private List<Order> orders;
    private List<Driver> drivers;
    private List<Vehicle> vehicles;

    private int[] vehicleStats;
    private int[] driverStats;

    public InfoContainer() {

        Driver driver1 = new Driver(1, "Ivan", "Ivanov", 1);
        Driver driver2 = new Driver(2, "Petr", "Petrov", 1);
        Driver driver3 = new Driver(3, "Egor", "Egorov", 2);
        Driver driver4 = new Driver(4, "Gleb", "Glebov", 3);
        Driver driver5 = new Driver(5, "Arsan", "Bagirov", 0);

        List<Driver> drivers1 = List.of(driver1, driver2);
        List<Driver> drivers2 = List.of(driver3);
        List<Driver> drivers3 = List.of(driver4);


        Vehicle vehicle1 = new Vehicle("A000AA", 1, true);
        Vehicle vehicle2 = new Vehicle("B000BB", 2, true);
        Vehicle vehicle3 = new Vehicle("C000CC", 3, true);
        Vehicle vehicle4 = new Vehicle("D000DD", 0, false);
        Vehicle vehicle5 = new Vehicle("E000EE", 0, true);
        Vehicle vehicle6 = new Vehicle("F000FF", 0, true);


        Cargo cargo1 = new Cargo("Cargo1", 100);
        Cargo cargo2 = new Cargo("Cargo2", 34);
        Cargo cargo3 = new Cargo("Cargo3", 41);
        Cargo cargo4 = new Cargo("Cargo4", 67);


        Waypoint waypoint1 = new Waypoint(cargo1, 1, "City1", WaypointType.LOAD);
        Waypoint waypoint2 = new Waypoint(cargo1, 1, "City2", WaypointType.UNLOAD);
        Waypoint waypoint3 = new Waypoint(cargo2, 1, "City3", WaypointType.LOAD);
        Waypoint waypoint4 = new Waypoint(cargo2, 1, "City2", WaypointType.UNLOAD);

        Waypoint waypoint5 = new Waypoint(cargo3, 1, "City1", WaypointType.LOAD);
        Waypoint waypoint6 = new Waypoint(cargo3, 1, "City1", WaypointType.UNLOAD);


        Waypoint waypoint7 = new Waypoint(cargo4, 1, "City1", WaypointType.LOAD);
        Waypoint waypoint8 = new Waypoint(cargo4, 1, "City1", WaypointType.UNLOAD);

        List<Waypoint> waypoints1 = List.of(waypoint1, waypoint2, waypoint3, waypoint4);
        List<Waypoint> waypoints2 = List.of(waypoint5, waypoint6);
        List<Waypoint> waypoints3 = List.of(waypoint7, waypoint8);


        Order order1 = new Order(1, "01.02.20", OrderStatus.ASSIGNED, vehicle1, drivers1, waypoints1);
        Order order2 = new Order(2, "02.02.20", OrderStatus.IN_PROGRESS, vehicle2, drivers2, waypoints2);
        Order order3 = new Order(3, "03.02.20", OrderStatus.ASSIGNED, vehicle3, drivers3, waypoints3);

        orders = List.of(order1, order2, order3);
        drivers = List.of(driver1, driver2, driver3, driver4, driver5);
        vehicles = List.of(vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6);

        calculateStats();
    }

    private void calculateStats() {
        int count = vehicles.size();
        int inOrder = (int) vehicles.stream().filter(v -> v.getOrderId() != 0).count();
        int broken = count - (int) vehicles.stream().filter(Vehicle::isOk).count();

        vehicleStats = new int[]{count, inOrder, broken};

        count = drivers.size();
        inOrder = (int) drivers.stream().filter(d -> d.getOrderId() != 0).count();

        driverStats = new int[]{count, inOrder};
    }
}

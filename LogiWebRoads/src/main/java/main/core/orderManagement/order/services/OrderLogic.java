package main.core.orderManagement.order.services;

import main.core.orderManagement.cargo.DTO.NewCargoDTO;
import main.core.orderManagement.cargo.entity.Cargo;
import main.core.orderManagement.cargo.entity.CargoStatus;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.DTO.NewOrderDTO;
import main.core.orderManagement.order.entity.Order;
import main.core.cityAndRoads.roads.entity.Road;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.orderManagement.waypoint.entity.WaypointType;
import main.core.vehicle.entity.Vehicle;
import main.global.exceptionHandling.NullChecker;

import java.util.*;

import static main.core.orderManagement.waypoint.entity.WaypointType.LOAD;

public class OrderLogic {
    private static final int MILLISECONDS_IN_HOUR = 3_600_000;
    private static final int WORKING_HOURS_PER_MONTH = 176;
    private static final int MAX_HOURS_IN_MONTH = 31 * 24;

    private final NullChecker nullChecker;

    public OrderLogic(NullChecker nullChecker) {
        this.nullChecker = nullChecker;
    }

    public int calculateMaxLoad(List<Waypoint> waypoints) {

        waypoints.sort(Comparator.comparingInt(Waypoint::getPathIndex));

        int maxLoad = 0;
        int currentLoad = 0;
        for (Waypoint w : waypoints) {
            int weight = w.getCargo().getWeight();
            if (w.getType() == LOAD) {
                currentLoad += weight;
                maxLoad = Math.max(currentLoad, maxLoad);
            } else {
                currentLoad -= weight;
            }
        }
        return maxLoad;
    }

    public int calculateOrderWorkTimeFirstMonth(Order order) {
        long start = System.currentTimeMillis();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(start));
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        int firstMonthHoursRemaining = (int) (c.getTimeInMillis() - start) / MILLISECONDS_IN_HOUR;

        return Math.min(firstMonthHoursRemaining, calcOrderLength(order));
    }

    public int calcOrderLength(Order order) {
        int orderLength = 0;
        for (Waypoint w : order.getWaypoints()) {
            orderLength += w.getPathLength();
        }
        return orderLength;
    }

    //TODO Написать логику подсчета пути!!!!!!
    public void calculateRoute(Order order, List<Road> roadMap) {
        List<Waypoint> waypoints = order.getWaypoints();
        int count = 1;
        for (Waypoint w : waypoints) {
            w.setPathIndex(count++);
        }

        calculateRouteLength(waypoints, roadMap);
    }

    //TODO Написать логику подсчета пути!!!!!! (Дейкстра)
    private void calculateRouteLength(List<Waypoint> waypoints, List<Road> roadMap) {
        // В каждом waypoint'e хранится длина пути от предыдущего.
        // У первого - путь до первого waypoint'a если водитель
        // находится в другом городе
        for (Waypoint w : waypoints) {
            w.setPathLength(10);
        }
    }

    public int getHoursPerWorker(Order order) {
        Vehicle vehicle = order.getAssignedVehicle();
        nullChecker.throwNotAssignedIfNull(vehicle, Vehicle.class, order.getId());

        int dutySize = order.getAssignedVehicle().getDutySize();

        return (int) Math.ceil((double) calculateOrderWorkTimeFirstMonth(order) / dutySize);
    }

    public Order getOrderFromDTO(NewOrderDTO dto) {
        Order order = new Order();
        List<Waypoint> waypoints = new ArrayList<>();

        dto.getDeliveryObjects().forEach(d -> {
            NewCargoDTO cargoDTO = d.getCargo();

            Cargo cargo = new Cargo(cargoDTO.getName(), cargoDTO.getWeight(), CargoStatus.PREPARED);

            int fromCityId = d.getCityIdFrom();
            int toCityId = d.getCityIdTo();

            Waypoint from = waypointFromDeliveryObj(fromCityId, order, cargo, WaypointType.LOAD);
            Waypoint to = waypointFromDeliveryObj(toCityId, order, cargo, WaypointType.UNLOAD);
            waypoints.add(from);
            waypoints.add(to);
        });

        order.setCreationDate(new Date());
        order.setWaypoints(waypoints);
        order.setStatus(OrderStatus.ASSIGNED);

        return order;
    }

    private Waypoint waypointFromDeliveryObj(int cityId, Order o, Cargo cargo, WaypointType type) {
        City city = new City();
        city.setId(cityId);

        return new Waypoint(city, cargo, type, 0, 0, false, o);
    }

    public int calculateMinDutySize(Order order) {
        int workingHoursFirstMonth = calculateOrderWorkTimeFirstMonth(order);
        int orderLength = calcOrderLength(order);
        int hoursAfterFirstMonth = orderLength - workingHoursFirstMonth;

        double maxWorkHoursPerMonth = Math.max(workingHoursFirstMonth, hoursAfterFirstMonth);
        maxWorkHoursPerMonth = Math.min(maxWorkHoursPerMonth,MAX_HOURS_IN_MONTH);

        return (int) Math.ceil(maxWorkHoursPerMonth / WORKING_HOURS_PER_MONTH);
    }
}

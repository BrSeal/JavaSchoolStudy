package main.core.orderManagement.order.services;

import main.core.orderManagement.order.entity.Order;
import main.core.cityAndRoads.roads.entity.Road;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.vehicle.entity.Vehicle;
import main.global.exceptionHandling.NullChecker;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static main.core.orderManagement.waypoint.entity.WaypointType.LOAD;

public class OrderLogic {
    private static final String CORRUPTED_DATA= "Data corrupted! Empty waypoint list!";

    private final NullChecker nullChecker;

    public OrderLogic(NullChecker nullChecker) {
        this.nullChecker = nullChecker;
    }

    public int calculateMaxLoad(List<Waypoint> waypoints) {
        nullChecker.throwNotFoundIfNull(waypoints,CORRUPTED_DATA);

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

    /*TODO написать логигку подсчета времени заказа с учетом смены месяцев(
       нужна только первая часть, так как вторая у всех водителей будет полная)
       (рассчитывается по карте городов и путевым точкам)
       Сейчас считается с момента создания заказа!!!
     */
    public int calculateOrderWorkTimeFirstMonth(Order order){
        Date start=order.getCreationDate();
        Calendar c=Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.MONTH,1);
        c.set(Calendar.DAY_OF_MONTH,1);
        int result=(int)(c.getTimeInMillis()-start.getTime())/3_600_000;
        for (Waypoint w : order.getWaypoints()) {
            result -= w.getPathLength();
        }

        return result;
    }

    //TODO Написать логику подсчета пути!!!!!!
    public void calculateRoute(Order order,List<Road> roadMap) {
        List<Waypoint> waypoints=order.getWaypoints();
        int count = 1;
        for (Waypoint w : waypoints) {
            w.setPathIndex(count++);
        }

        calculateRouteLength(waypoints,roadMap);
    }

    //TODO Написать логику подсчета пути!!!!!! (Дейкстра)
    private void calculateRouteLength(List<Waypoint> waypoints,List<Road> roadMap){
        // В каждом waypoint'e хранится длина пути от предыдущего.
        // У первого - путь до первого waypoint'a если водитель
        // находится в другом городе
        for (Waypoint w : waypoints) {
             w.setPathLength(10);
        }
    }

    public int getHoursPerWorker(Order order) {
        Vehicle vehicle= order.getAssignedVehicle();
        nullChecker.throwNotAssignedIfNull(vehicle,Vehicle.class,order.getId());

        int dutySize = order.getAssignedVehicle().getDutySize();

        return (int) Math.ceil((double) calculateOrderWorkTimeFirstMonth(order) / dutySize);
    }
}

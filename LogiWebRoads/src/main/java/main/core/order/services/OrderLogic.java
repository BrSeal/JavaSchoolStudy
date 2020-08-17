package main.core.order.services;

import main.model.logistic.Order;
import main.model.logistic.Road;
import main.model.logistic.Waypoint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static main.model.logistic.WaypointType.LOAD;

public class OrderLogic {

    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
    public static int calculateMaxLoad(List<Waypoint> waypoints) {
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
     */
    public static int calculateOrderWorkTimeFirstMonth(Order order){
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
    public static void calculateRoute(Order order,List<Road> roadMap) {
        List<Waypoint> waypoints=order.getWaypoints();
        int count = 1;
        for (Waypoint w : waypoints) {
            w.setPathIndex(count++);
        }

        calculateRouteLength(waypoints,roadMap);
    }

    //TODO Написать логику подсчета пути!!!!!! (Дейкстра)
    private static void calculateRouteLength(List<Waypoint> waypoints,List<Road> roadMap){
        // В каждом waypoint'e хранится длина пути от предыдущего.
        // У первого - путь до первого waypoint'a если водитель
        // находится в другом городе
        for (Waypoint w : waypoints) {
             w.setPathLength(10);
        }
    }
}

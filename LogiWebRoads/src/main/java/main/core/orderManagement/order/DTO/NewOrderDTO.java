package main.core.orderManagement.order.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.cargo.entity.Cargo;
import main.core.cargo.entity.CargoStatus;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.entity.OrderStatus;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.orderManagement.waypoint.entity.WaypointType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderDTO implements OrderDTO {
    private static final String EMPTY_WAYPOINTS_LIST_ERR = "Add some cargo to deliver!";

    List<DeliveryObject> deliveryObjects;

    public Order toOrder() {
        Order order = new Order();
        List<Waypoint> waypoints = new ArrayList<>();

        if(deliveryObjects==null||deliveryObjects.isEmpty()) throw new IllegalArgumentException(EMPTY_WAYPOINTS_LIST_ERR);

        deliveryObjects.forEach(obj -> {

            Cargo cargo = obj.getCargo().toCargo();
            validateDeliveryObject(obj);

            cargo.setStatus(CargoStatus.PREPARED);
            int from = obj.getCityIdFrom();
            int to = obj.getCityIdTo();


            Waypoint waypointFrom = waypointFromDeliveryObj(from, order, cargo, WaypointType.LOAD);
            Waypoint waypointTo = waypointFromDeliveryObj(to, order, cargo, WaypointType.UNLOAD);

            waypoints.add(waypointFrom);
            waypoints.add(waypointTo);
        });

        order.setCreationDate(new Date());
        order.setWaypoints(waypoints);
        order.setStatus(OrderStatus.ASSIGNED);

        return order;
    }

    private Waypoint waypointFromDeliveryObj(int cityId, Order o, Cargo c, WaypointType type) {
        if (cityId<=0) throw new IllegalArgumentException("City id can't be 0 or less!");
        City city = new City();
        city.setId(cityId);

        return new Waypoint(city, c, type, 0,0, false, o);
    }

    private void validateDeliveryObject(DeliveryObject d){
        String cargoName=d.getCargo().getName();
        int from=d.getCityIdFrom();
        int to=d.getCityIdTo();

        if(from==0){
            String errMsg=String.format("Please choose city from of cargo %s!",cargoName);
            throw new IllegalArgumentException(errMsg);
        }
        if(to==0) {
            String errMsg=String.format("Please choose city to of cargo %s!",cargoName);
            throw new IllegalArgumentException(errMsg);
        }
    }
}

package main.core.order.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderDTO implements OrderDTO {
    List<DeliveryObject> deliveryObjects;

    public Order toOrder() {
        Order order = new Order();
        List<Waypoint> waypoints = new ArrayList<>();

        deliveryObjects.forEach(obj -> {

            Cargo cargo = obj.getCargo().toCargo();
            cargo.setStatus(CargoStatus.PREPARED);
            int from = obj.getCityIdFrom();

            int to = obj.getCityIdTo();

            if(from==0) throw new IllegalArgumentException("City from is undefined!");
            if(to==0) throw new IllegalArgumentException("City from is undefined!");

            Waypoint waypointFrom = waypointFromDeliveryObj(from, order, cargo, WaypointType.LOAD);
            Waypoint waypointTo = waypointFromDeliveryObj(to, order, cargo, WaypointType.UNLOAD);

            waypoints.add(waypointFrom);
            waypoints.add(waypointTo);
        });

        order.setCreationDate(new Date());
        order.setWaypoints(waypoints);

        return order;
    }

    private Waypoint waypointFromDeliveryObj(int cityId, Order o, Cargo c, WaypointType type) {
        if (cityId<=0) throw new IllegalArgumentException("City id can't be 0 or less!");
        City city = new City();
        city.setId(cityId);

        return new Waypoint(city, c, type, 0,0, false, o);
    }
}

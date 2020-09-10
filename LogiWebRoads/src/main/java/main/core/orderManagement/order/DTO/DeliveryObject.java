package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.cargo.DTO.NewCargoDTO;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.orderManagement.waypoint.entity.WaypointType;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryObject {
    private NewCargoDTO cargo;
    private int cityIdFrom;
    private int cityIdTo;

    public Waypoint toWaypoint(WaypointType type,Order order){
        City city = new City();
        city.setId(type==WaypointType.LOAD?cityIdFrom:cityIdTo);


        return new Waypoint(city, cargo.toCargo(), type, 0, 0, false, order);
    }
}

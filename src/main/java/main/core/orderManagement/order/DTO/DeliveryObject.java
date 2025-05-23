package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.cargo.DTO.NewCargoDTO;
import main.core.orderManagement.cargo.entity.Cargo;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.orderManagement.waypoint.entity.WaypointType;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryObject {
    private NewCargoDTO cargo;
    private int cityIdFrom;
    private int cityIdTo;

    public List<Waypoint> toWaypoints(Order order) {
        ArrayList<Waypoint> waypoints = new ArrayList<>();
        City cityFrom = new City();
        City cityTo = new City();
        cityFrom.setId(cityIdFrom);
        cityTo.setId(cityIdTo);

        Cargo cargoFromDto = this.cargo.toCargo();
        Waypoint from = new Waypoint(cityFrom, cargoFromDto, WaypointType.LOAD, 0, 0, false, order);
        Waypoint to = new Waypoint(cityTo, cargoFromDto, WaypointType.UNLOAD, 0, 0, false, order);

        waypoints.add(from);
        waypoints.add(to);

        return waypoints;
    }
}

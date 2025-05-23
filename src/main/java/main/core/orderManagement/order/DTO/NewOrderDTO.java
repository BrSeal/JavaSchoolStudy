package main.core.orderManagement.order.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class NewOrderDTO {
    private List<DeliveryObject> deliveryObjects;

    public Order toOrder() {
        Order order = new Order();
        List<Waypoint> waypoints = new ArrayList<>();

        deliveryObjects.forEach(d -> {
            waypoints.addAll(d.toWaypoints(order));
        });

        order.setCreationDate(new Date());
        order.setWaypoints(waypoints);
        order.setStatus(OrderStatus.ASSIGNED);

        return order;
    }
}

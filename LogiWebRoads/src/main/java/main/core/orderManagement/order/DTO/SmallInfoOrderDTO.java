package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.orderManagement.order.entity.OrderStatus;
import main.global.exceptionHandling.exceptions.DtoConvertForbiddenException;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.waypoint.entity.Waypoint;

import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmallInfoOrderDTO{
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private int id;
    private String date;
    private OrderStatus status;

    public SmallInfoOrderDTO(Order order) {
        id = order.getId();
        date = dateFormat.format(order.getCreationDate());
        status = order.getStatus();
    }
}

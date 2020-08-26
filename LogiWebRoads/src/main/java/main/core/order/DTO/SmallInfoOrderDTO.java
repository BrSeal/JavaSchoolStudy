package main.core.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.exceptionHandling.exceptions.DtoConvertForbiddenException;
import main.model.logistic.Order;
import main.model.logistic.Waypoint;

import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmallInfoOrderDTO implements OrderDTO {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private int id;
    private String date;
    private boolean started;
    private boolean completed;

    public SmallInfoOrderDTO(Order order) {
        id = order.getId();
        date = dateFormat.format(order.getCreationDate());
        completed = order.isCompleted();
        started = order.getWaypoints().stream().anyMatch(Waypoint::isDone);
    }

    @Override
    public Order toOrder() {
        throw new DtoConvertForbiddenException();
    }
}

package main.core.waypoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.logistic.Cargo;
import main.model.logistic.City;
import main.model.logistic.orderAndWaypoint.Waypoint;
import main.model.logistic.orderAndWaypoint.WaypointType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaypointDTO {

    private int id;
    private boolean isDone;
    private int cityId;
    private String cityName;
    private WaypointType type;
    private Cargo cargo;
    private int amount;

    public WaypointDTO(Waypoint w) {
        this.id = w.getId();
        this.isDone = w.isDone();
        this.cityId = w.getCity().getId();
        this.cityName = w.getCity().getName();
        this.type = w.getType();
        this.cargo = w.getCargo();
        this.amount = w.getAmount();
    }

    public Waypoint toWaypoint() {
        City city = new City();
        city.setId(this.cityId);
        Waypoint w = new Waypoint(isDone, city, cargo, amount, type);
        w.setId(id);
        return w;
    }


}

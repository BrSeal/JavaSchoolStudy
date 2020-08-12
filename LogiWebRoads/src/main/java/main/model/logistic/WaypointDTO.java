package main.model.logistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WaypointDTO {

    private int id;
    private boolean isDone;
    private int city;
    private WaypointType type;
    private Cargo cargo;
    private int amount;

    public Waypoint toWaypoint() {
        City city = new City();
        city.setId(this.city);

        Waypoint w = new Waypoint(isDone, city, cargo, amount, type);
        w.setId(id);
        return w;
    }


}

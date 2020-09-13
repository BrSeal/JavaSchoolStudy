package main.DTO;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Waypoint {
    Cargo cargo;
    int pathIndex;
    String city;
    WaypointType type;
}

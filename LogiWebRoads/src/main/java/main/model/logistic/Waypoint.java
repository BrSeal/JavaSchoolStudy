package main.model.logistic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.IdClass;

import javax.persistence.*;


@Entity
@Table(name = "waypoints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Waypoint extends IdClass {

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WaypointType type;

    @Column(name = "path_index")
    private int pathIndex;

    @Column(name = "road_from_prev_waypoint_length")
    private int pathLength;

    @Column(name = "is_done")
    private boolean done;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }
    )
    @JoinColumn(name = "order_id")
    Order order;
}

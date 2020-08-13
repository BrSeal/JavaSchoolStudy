package main.model.logistic.orderAndWaypoint;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.IdClass;
import main.model.logistic.Cargo;
import main.model.logistic.City;

import javax.persistence.*;


@Entity
@Table(name = "waypoints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Waypoint extends IdClass {

    @Column(name = "isDone")
    private boolean isDone = false;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "сargo_id")
    private Cargo cargo;

    @Column(name = "amount")
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WaypointType type;
}

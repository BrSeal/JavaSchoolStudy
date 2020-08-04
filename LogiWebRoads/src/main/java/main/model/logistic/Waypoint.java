package main.model.logistic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.IdClass;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "waypoints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Waypoint extends IdClass {

    @OneToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "waypoint_id")
    private List<Cargo> cargo;

    @Column(name = "amount")
    private int amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WaypointType type;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "isDone")
    private boolean isDone=false;
}

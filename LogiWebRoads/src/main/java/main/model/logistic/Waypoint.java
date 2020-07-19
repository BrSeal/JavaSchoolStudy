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

    @Column(name = "cargo_id")
    @OneToOne
    private Cargo cargo;

    @Column(name = "amount")
    private int amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WaypointType type;
}

package main.model.logistic;


import lombok.*;
import main.model.IdClass;
import main.model.users.Driver;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends IdClass {

    @Column(name = "is_completed")
    private boolean isCompleted;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<Driver> driversInOrder;

    @Column(name = "assigned_vehicle")
    @OneToOne
    private Vehicle assignedVehicle;

    @OneToMany(targetEntity = Waypoint.class, mappedBy = "id", cascade = CascadeType.ALL)
    private List<Waypoint> waypoints;
}

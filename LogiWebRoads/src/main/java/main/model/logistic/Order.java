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

    @OneToMany(mappedBy = "currentOrder")
    private List<Driver> driversInOrder;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "assigned_vehicle_id")
    private Vehicle assignedVehicle;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Waypoint> waypoints;
}

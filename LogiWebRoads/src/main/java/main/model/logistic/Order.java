package main.model.logistic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Waypoint> waypoints;

    @OneToOne
    private Vehicle assignedVehicle;


    //TODO Перенести CurrentOrder в Driver.class
    @OneToMany
    @JoinColumn(name = "assigned_order")
    private List<Driver> assignedDrivers;
}

package main.model.logistic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.IdClass;
import main.model.users.Driver;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends IdClass {

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Waypoint> waypoints;

    @OneToMany(mappedBy = "currentOrder", cascade = CascadeType.ALL)
    private List<Driver> assignedDrivers;

    @OneToOne(mappedBy = "currentOrder", cascade =CascadeType.ALL)
    private Vehicle assignedVehicle;
}

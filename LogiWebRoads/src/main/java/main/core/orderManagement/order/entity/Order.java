package main.core.orderManagement.order.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.global.mappedSuperclass.IdClass;
import main.core.driver.entity.Driver;
import main.core.vehicle.entity.Vehicle;
import main.core.orderManagement.waypoint.entity.Waypoint;

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

    @Column(name = "status")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Waypoint> waypoints;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Driver> assignedDrivers;

    @OneToOne(cascade =CascadeType.ALL)
    private Vehicle assignedVehicle;

}

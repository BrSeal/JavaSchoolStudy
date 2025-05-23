package main.core.orderManagement.order.entity;


import lombok.*;
import main.core.driver.entity.Driver;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.core.vehicle.entity.Vehicle;
import main.global.mappedSuperclass.IdClass;

import jakarta.persistence.*;
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

    @OneToMany
    private List<Driver> assignedDrivers;

    @OneToOne(cascade =CascadeType.ALL)
    private Vehicle assignedVehicle;
}

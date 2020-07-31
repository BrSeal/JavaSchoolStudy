package main.model.users;

import lombok.*;
import main.model.IdClass;


import main.model.logistic.City;
import main.model.logistic.Order;
import main.model.logistic.Vehicle;

import javax.persistence.*;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends IdClass {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "hours_worked_current_month")
    private int hoursWorked;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DriverStatus status= DriverStatus.ON_REST;

    @ManyToOne
    @JoinColumn(name = "current_vehicle")
    private Vehicle currentVehicle;

    @ManyToOne
    @JoinColumn(name = "current_city_id", nullable = false)
    private City currentCity;

    @ManyToOne
    @JoinColumn(name = "current_order_id")
    private Order currentOrder;
}

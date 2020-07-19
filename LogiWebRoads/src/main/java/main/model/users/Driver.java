package main.model.users;

import main.model.IdClass;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import main.model.logistic.City;
import main.model.logistic.Order;
import main.model.logistic.Vehicle;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drivers")
@Data
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
    @Column(name = "status", columnDefinition = "enum")
    private DriverStatus status;

    @Column(name = "current_vehicle")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Vehicle currentVehicle;

    @Column(name = "current_city")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private City currentCity;

    @ManyToOne (cascade = CascadeType.ALL)
    private List<Order> orders;
}

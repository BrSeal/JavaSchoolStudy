package main.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.IdClass;
import main.model.logistic.City;

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
    private DriverStatus status = DriverStatus.ON_REST;

    @ManyToOne
    @JoinColumn(name = "current_city_id")
    private City currentCity;
}

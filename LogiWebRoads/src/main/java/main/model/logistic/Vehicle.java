package main.model.logistic;

import lombok.*;
import main.model.IdClass;
import main.model.users.Driver;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends IdClass {

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "duty_size")
    private int dutySize;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "is_ok")
    private boolean isOk;

    @Column(name = "current_city")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private City currentCity;

    @OneToMany(targetEntity = Driver.class, mappedBy = "current_city", cascade = CascadeType.ALL)
    private List<Driver> driversOnDuty;

    @Column(name = "current_order")
    @OneToOne
    private Order currentOrder;
}

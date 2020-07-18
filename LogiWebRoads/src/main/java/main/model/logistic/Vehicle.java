package main.model.logistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.model.IdClass;
import main.model.users.Driver;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends IdClass {

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "duty_size")
    private int dutySize;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "isOk")
    private boolean isOk;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private City currentCity;

    @OneToMany(targetEntity = Driver.class, mappedBy = "current_city", cascade = CascadeType.ALL)
    private List<Driver> driversOnDuty;
}

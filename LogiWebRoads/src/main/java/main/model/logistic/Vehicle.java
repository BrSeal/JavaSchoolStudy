package main.model.logistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.IdClass;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "current_city_id")
    private City currentCity;
}

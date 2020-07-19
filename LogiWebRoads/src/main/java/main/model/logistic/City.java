package main.model.logistic;


import lombok.*;
import main.model.IdClass;
import main.model.users.Driver;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends IdClass {
    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Driver.class, mappedBy = "current_city", cascade = CascadeType.ALL)
    private List<Driver> drivers;

    @OneToMany(targetEntity = Vehicle.class, mappedBy = "current_city", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
}

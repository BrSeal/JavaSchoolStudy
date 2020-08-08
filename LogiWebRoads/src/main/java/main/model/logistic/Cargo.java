package main.model.logistic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.IdClass;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends IdClass {

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CargoStatus status;


}

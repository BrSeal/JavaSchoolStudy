package main.model.logistic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.model.IdClass;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends IdClass {

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "enum")
    private CargoStatus status;
}

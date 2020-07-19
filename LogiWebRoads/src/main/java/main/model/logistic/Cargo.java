package main.model.logistic;


import lombok.*;
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
    @Column(name = "status", columnDefinition = "enum")
    private CargoStatus status;
}

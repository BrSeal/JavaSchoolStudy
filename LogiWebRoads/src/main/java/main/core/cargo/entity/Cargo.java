package main.core.cargo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.global.mappedSuperclass.IdClass;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends IdClass {

    @Column(name = "name")
    @Size(min = 1, max=255)
    private String name;

    @Column(name = "weight")
    @Min(1)
    @Max(1000)
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CargoStatus status;
}

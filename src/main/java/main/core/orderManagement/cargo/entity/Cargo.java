package main.core.orderManagement.cargo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.global.mappedSuperclass.IdClass;


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

package main.core.cityAndRoads.cities.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.global.mappedSuperclass.IdClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends IdClass {
    @Column(name = "name")
    @Size(min = 3, max = 50)
    private String name;
}

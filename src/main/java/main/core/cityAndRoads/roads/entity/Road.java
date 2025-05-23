package main.core.cityAndRoads.roads.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.cityAndRoads.cities.entity.City;
import main.global.mappedSuperclass.IdClass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "roads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Road extends IdClass {

    @Column(name = "length")
    private int length;

    @OneToOne
    private City from;

    @OneToOne
    private City to;
}

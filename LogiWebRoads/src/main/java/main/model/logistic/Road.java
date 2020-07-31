package main.model.logistic;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import main.model.IdClass;

import javax.persistence.*;


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

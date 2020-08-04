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
}

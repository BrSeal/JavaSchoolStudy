package main.core.employee.entity;


import lombok.*;
import main.core.Security.entity.User;
import main.global.mappedSuperclass.IdClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee extends IdClass {
    @OneToOne
    private User user;
}

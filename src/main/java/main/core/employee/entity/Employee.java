package main.core.employee.entity;


import lombok.*;
import main.core.security.entity.User;
import main.global.mappedSuperclass.IdClass;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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

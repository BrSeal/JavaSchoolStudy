package main.core.employee.entity;


import lombok.*;
import main.global.mappedSuperclass.IdClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee extends IdClass {
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
}

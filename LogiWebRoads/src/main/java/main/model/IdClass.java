package main.model;

import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Getter
public class IdClass {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}

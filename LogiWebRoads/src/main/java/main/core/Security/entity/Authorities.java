package main.core.Security.entity;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(AuthoritiesPK.class)
@Table(name = "authorities")
public class Authorities {
    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Id
    @Column(name = "authority")
    private UserRole authority;
}

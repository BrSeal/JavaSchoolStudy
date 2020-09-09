package main.core.Security.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username",
            unique = true,
            nullable = false,
            length = 50)
    private String username;

    @Column(name = "password",
            nullable = false,
            length = 50)
    private String password;

    @Column(name = "enabled",
            nullable = false)
    private boolean enabled;

    @Column(name = "authorities")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Authority> authorities;
}

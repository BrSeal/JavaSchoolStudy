package main.core.Security.entity;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthoritiesPK implements Serializable {
    private User user;
    private UserRole authority;
}

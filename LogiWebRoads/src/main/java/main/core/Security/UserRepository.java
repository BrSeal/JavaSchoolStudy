package main.core.security;


import main.core.security.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User get(String username);

    String save(User driver);

    void update(User driver);

    void delete(User driver);
}

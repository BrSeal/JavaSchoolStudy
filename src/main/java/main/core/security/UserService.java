package main.core.security;

import main.core.security.entity.User;

public interface UserService {
    User get(String username);

    String saveEmployee(User user);

    String saveDriver(User user);

    void update(User user);

    void delete(User user);
}

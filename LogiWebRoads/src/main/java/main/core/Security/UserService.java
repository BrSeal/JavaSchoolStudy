package main.core.Security;

import main.core.Security.entity.User;

public interface UserService {
    User get(String username);

    String saveEmployee(User user);

    String saveDriver(User user);

    void update(User user);

    void delete(User user);
}

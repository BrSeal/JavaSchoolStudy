package main.core.Security;

import main.core.Security.entity.Authority;
import main.core.Security.entity.User;
import main.core.Security.entity.UserRole;
import main.global.exceptionHandling.NullChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final String NO_USER_ERR="Save failed! No user to save!";
    private static final String ENCRYPT = "{noop}";


    private final NullChecker nullChecker;
    private final UserRepository userRepository;
    private final UserCheckProvider userCheckProvider;
    @Autowired
    public UserServiceImpl(NullChecker nullChecker, UserRepository userRepository, UserCheckProvider userCheckProvider) {
        this.nullChecker = nullChecker;
        this.userRepository = userRepository;
        this.userCheckProvider = userCheckProvider;
    }

    @Override
    public User get(String username) {
        User user = userRepository.get(username);
        nullChecker.throwNotFoundIfNull(user, User.class, username);
        return user;
    }

    @Override
    public String saveEmployee(User user) {
        return save(user,UserRole.ROLE_EMPLOYEE);
    }

    @Override
    public String saveDriver(User user) {
        return save(user,UserRole.ROLE_DRIVER);
    }

    @Override
    public void update(User user) {
        List<User> users = userRepository.getAll();

        userCheckProvider.updateCheck(user, users);
        userRepository.update(user);
    }

    @Override
    public void delete(User userToDelete) {
        User user = userRepository.get(userToDelete.getUsername());
        user.setEnabled(false);

        userRepository.update(user);
    }


    private String save(User user, UserRole role){
        nullChecker.throwNotFoundIfNull(user,NO_USER_ERR);

        List<User> users = userRepository.getAll();

        Set<Authority> authorities=new HashSet<>();
        authorities.add(new Authority(0,user, UserRole.ROLE_USER));
        authorities.add(new Authority(0,user, role));

        user.setAuthorities(authorities);

        userCheckProvider.saveCheck(user, users);

        user.setPassword(ENCRYPT+user.getPassword());
        return userRepository.save(user);
    }

}

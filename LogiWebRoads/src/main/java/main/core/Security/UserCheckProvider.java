package main.core.Security;

import main.core.Security.entity.Authority;
import main.core.Security.entity.User;
import main.global.exceptionHandling.exceptions.SaveFailedException;

import java.util.List;
import java.util.Set;

public class UserCheckProvider {
    private static final String USERNAME_ERR = "Username is null or empty!";
    private static final String USERNAME_ALREADY_EXIST_ERR = "Username is already in use!";
    private static final String PASSWORD_ERR = "Password is null or empty!";
    private static final String NO_AUTHORITIES_ERR = "No authorities presented for user %s!";
    private static final String NO_USER_IN_AUTHORITY = "User is null in authority!";
    private static final String NO_ROLE_IN_AUTHORITY = "Role is null in authority!";
    private static final String NO_AUTHORITY = "Authority of the new user is null!";
    private static final String FAIL = "Failed to %s user! ";
    private static final String SAVE = "save";
    private static final String UPDATE = "update";


    public void saveCheck(User user, List<User> users){
      validateUser(user,users,SAVE);
    }

    public void updateCheck(User user,List<User> users){
        validateUser(user,users,UPDATE);
    }

    private void validateUser(User user, List<User> users, String action){
        String fail=String.format(FAIL, action);

        String username=user.getUsername();
        String password=user.getPassword();
        Set<Authority> authorities=user.getAuthorities();

        if(username==null||username.isEmpty()) throw new SaveFailedException(fail+USERNAME_ERR);
        if(users.stream().anyMatch(u->u.getUsername().equals(username))) throw new SaveFailedException(fail+USERNAME_ALREADY_EXIST_ERR);

        if(password==null||password.isEmpty()) throw new SaveFailedException(fail+PASSWORD_ERR);
        if(authorities==null||authorities.size()==0) {
            String errMsg=String.format(fail+NO_AUTHORITIES_ERR,username);
            throw new SaveFailedException(errMsg);
        }

        authorities.forEach(authority -> {
            if(authority==null)throw new SaveFailedException(fail+NO_AUTHORITY);
            User authorityUser=authority.getUser();

            if(authorityUser==null||authorityUser!=user)throw new SaveFailedException(fail+NO_USER_IN_AUTHORITY);
            if(authority.getRole()==null) throw new SaveFailedException(fail+NO_ROLE_IN_AUTHORITY);
        });
    }
}

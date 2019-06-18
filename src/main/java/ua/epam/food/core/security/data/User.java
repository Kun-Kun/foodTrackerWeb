package ua.epam.food.core.security.data;

import java.util.List;

public interface User {

    String getUsername();
    boolean isEnabled();
    List<Role> getRoles();
    List<Privilege> getPrivileges();
    boolean isAuthenticated();
}

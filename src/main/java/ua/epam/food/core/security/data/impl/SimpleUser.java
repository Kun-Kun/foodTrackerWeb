package ua.epam.food.core.security.data.impl;

import ua.epam.food.core.security.data.Privilege;
import ua.epam.food.core.security.data.Role;
import ua.epam.food.core.security.data.User;

import java.util.List;

public class SimpleUser implements User {
    private String username;
    private boolean isEnabled;
    private List<Role> roles;
    private List<Privilege> privileges;
    private boolean isAuthenticated;

    public SimpleUser(String username, boolean isEnabled, List<Role> roles, List<Privilege> privileges, boolean isAuthenticated) {
        this.username = username;
        this.isEnabled = isEnabled;
        this.roles = roles;
        this.privileges = privileges;
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}

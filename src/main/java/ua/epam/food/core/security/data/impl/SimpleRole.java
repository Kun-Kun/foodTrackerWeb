package ua.epam.food.core.security.data.impl;

import ua.epam.food.core.security.data.Role;

public class SimpleRole implements Role {

    private String role;

    public SimpleRole(String role) {
        this.role = role;
    }

    @Override
    public String getRole() {
        return null;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role that = (Role) o;

        return role != null ? role.equals(that.getRole()) : that.getRole() == null;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }
}

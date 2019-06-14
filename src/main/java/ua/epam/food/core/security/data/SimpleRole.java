package ua.epam.food.core.security.data;

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
}

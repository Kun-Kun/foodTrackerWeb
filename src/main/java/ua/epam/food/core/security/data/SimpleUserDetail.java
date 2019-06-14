package ua.epam.food.core.security.data;

public class SimpleUserDetail implements UserDetail {
    private User user;
    private Object profile;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getProfile() {
        return profile;
    }

    public void setProfile(Object profile) {
        this.profile = profile;
    }
}

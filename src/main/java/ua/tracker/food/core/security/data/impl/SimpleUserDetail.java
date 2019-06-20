package ua.tracker.food.core.security.data.impl;

import ua.tracker.food.core.security.data.User;
import ua.tracker.food.core.security.data.UserDetail;

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

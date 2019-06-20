package ua.tracker.food.services;

import ua.tracker.food.core.security.data.UserDetail;

public interface UserDetailService {
    UserDetail loadUserByUsername(String username);
}

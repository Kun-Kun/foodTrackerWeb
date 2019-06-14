package ua.epam.food.services;

import ua.epam.food.core.security.data.UserDetail;

public interface UserDetailService {
    UserDetail loadUserByUsername(String username);
}

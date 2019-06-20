package ua.tracker.food.services;

import javax.servlet.http.HttpSession;

public interface UserAuthenticationService {
    boolean login(HttpSession session, String username, String password);
    void logout(HttpSession session);
}

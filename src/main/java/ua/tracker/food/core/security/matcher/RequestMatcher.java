package ua.tracker.food.core.security.matcher;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public interface RequestMatcher {
    boolean matches(ServletRequest request);
}

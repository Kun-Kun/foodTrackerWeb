package ua.epam.food.core.security.matcher.impl;

import ua.epam.food.core.security.context.SecurityContextHolder;
import ua.epam.food.core.security.data.UserDetail;
import ua.epam.food.core.security.matcher.RequestMatcher;

import javax.servlet.ServletRequest;

public class AuthenticatedSecurityContextMatcher implements RequestMatcher {
    private final boolean inversion;

    public AuthenticatedSecurityContextMatcher(boolean inversion) {
        this.inversion = inversion;
    }

    @Override
    public boolean matches(ServletRequest request) {
        Boolean returnValue;
        UserDetail userDetail = SecurityContextHolder.getInstance().getSecurityData();
        if (userDetail!=null&&userDetail.getUser()!=null){
            returnValue = userDetail.getUser().isAuthenticated();
        }else {
            returnValue = false;
        }
        if (inversion){
            return !returnValue;
        }else return returnValue;
    }
}

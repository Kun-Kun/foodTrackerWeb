package ua.epam.food.core.security.matcher.impl;

import ua.epam.food.core.security.context.SecurityContextHolder;
import ua.epam.food.core.security.data.Privilege;
import ua.epam.food.core.security.data.Role;
import ua.epam.food.core.security.data.UserDetail;
import ua.epam.food.core.security.matcher.RequestMatcher;

import javax.servlet.ServletRequest;

public class RoleSecurityContextMatcher implements RequestMatcher {
    private final boolean inversion;
    private final Role role;

    public RoleSecurityContextMatcher(Role role, boolean inversion) {
        this.inversion = inversion;
        this.role = role;
    }

    @Override
    public boolean matches(ServletRequest request) {
        Boolean returnValue;
        UserDetail userDetail = SecurityContextHolder.getInstance().getSecurityData();
        if (userDetail!=null&&userDetail.getUser()!=null){
            returnValue = userDetail.getUser().getRoles().contains(role);
        }else {
            returnValue = false;
        }
        if (inversion){
            return !returnValue;
        }else return returnValue;
    }
}

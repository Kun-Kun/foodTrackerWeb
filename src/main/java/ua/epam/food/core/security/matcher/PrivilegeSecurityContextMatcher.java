package ua.epam.food.core.security.matcher;

import ua.epam.food.core.security.context.SecurityContextHolder;
import ua.epam.food.core.security.data.Privilege;
import ua.epam.food.core.security.data.UserDetail;

import javax.servlet.ServletRequest;

public class PrivilegeSecurityContextMatcher implements RequestMatcher {
    private final boolean inversion;
    private final Privilege privilege;

    public PrivilegeSecurityContextMatcher(Privilege privilege, boolean inversion) {
        this.inversion = inversion;
        this.privilege = privilege;
    }

    @Override
    public boolean matches(ServletRequest request) {
        Boolean returnValue;
        UserDetail userDetail = SecurityContextHolder.getInstance().getSecurityData();
        if (userDetail!=null&&userDetail.getUser()!=null){
            returnValue = userDetail.getUser().getPrivileges().contains(privilege);
        }else {
            returnValue = false;
        }
        if (inversion){
            return !returnValue;
        }else return returnValue;
    }
}

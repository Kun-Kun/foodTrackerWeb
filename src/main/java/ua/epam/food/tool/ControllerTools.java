package ua.epam.food.tool;

import ua.epam.food.core.security.context.SecurityContextHolder;
import ua.epam.food.dto.Profile;

import javax.servlet.ServletRequest;

public class ControllerTools {

    public static String passParameterToAttribute(ServletRequest request, String parameter) {
        String value = request.getParameter(parameter);
        request.setAttribute(parameter,value);
        return value;
    }

    public static boolean isUserAuthenticated(){
        return SecurityContextHolder.getInstance().getSecurityData().getUser().isAuthenticated();
    }

    public static String getUsername(){
        return SecurityContextHolder.getInstance().getSecurityData().getUser().getUsername();
    }

    public static Profile getProfile(){
        return (Profile) SecurityContextHolder.getInstance().getSecurityData().getProfile();
    }
}

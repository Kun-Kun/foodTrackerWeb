package ua.epam.food.tool;

import javax.servlet.ServletRequest;

public class ControllerTools {
    public static String passParameterToAttribute(ServletRequest request, String parameter) {
        String value = request.getParameter(parameter);
        request.setAttribute(parameter,value);
        return value;
    }
}

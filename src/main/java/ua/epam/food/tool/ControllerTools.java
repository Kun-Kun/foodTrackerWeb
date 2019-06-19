package ua.epam.food.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import ua.epam.food.core.security.context.SecurityContextHolder;
import ua.epam.food.dto.Profile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerTools {
	
	private static ObjectMapper objectMapper = new ObjectMapper(); 

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
	public static void sendRedirectInternal(HttpServletRequest request, HttpServletResponse response, String path) throws IOException{
		String contextPath = request.getContextPath();
		StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(contextPath).append(path);
		if(request.isRequestedSessionIdFromURL()) {
            Integer sessionIdStartPosition = request.getRequestURL().indexOf(";");
            if (sessionIdStartPosition!=-1) {
                String seesionId = request.getRequestURL().substring(sessionIdStartPosition);
                pathBuilder.append(seesionId);
            }
        }
        response.sendRedirect(pathBuilder.toString());
	}

    public static boolean sendRedirectOrigin(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String origin = request.getParameter("origin");
        if(origin!=null&&!origin.isEmpty()) {
            response.sendRedirect(origin);
            return true;
        }else{
            return false;
        }
    }

    public static boolean sendRedirectOrigin(HttpServletRequest request, HttpServletResponse response, String path) throws IOException{
        String origin = request.getParameter("origin");
        if(origin!=null&&!origin.isEmpty()) {
            response.sendRedirect(origin);
            return true;
        }else{
            sendRedirectInternal(request,response,path);
            return false;
        }
    }

    public static void prepareHtmlPage(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
    }
    public static void prepareHtmlPage(HttpServletRequest request, HttpServletResponse response, String subHeadline) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("subHeadline", subHeadline);
    }

    public static void prepareHtmlPage(HttpServletRequest request, HttpServletResponse response, String headline, String subHeadline) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("headline", headline);
        request.setAttribute("subHeadline", subHeadline);
    }

	public static void returnJSON(HttpServletResponse response, Object object)throws IOException{
		String json = objectMapper.writeValueAsString(object);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(json);
		out.flush();
	}
}

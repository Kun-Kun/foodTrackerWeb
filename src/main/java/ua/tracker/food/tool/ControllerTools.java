package ua.tracker.food.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import ua.tracker.food.core.security.context.SecurityContextHolder;
import ua.tracker.food.dto.Profile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerTools {
	private static Logger log = LogManager.getLogger(ControllerTools.class);
	private static ObjectMapper objectMapper = new ObjectMapper(); 

    public static String passParameterToAttribute(ServletRequest request, String parameter) {
		log.log(Level.INFO, "Passing '{}' parameter to attribute",parameter);
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
		log.log(Level.INFO, "Sending internal redirect{}",path);
		String contextPath = request.getContextPath();
		StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(contextPath).append(path);
		if(request.isRequestedSessionIdFromURL()) {
            Integer sessionIdStartPosition = request.getRequestURL().indexOf(";");
            if (sessionIdStartPosition!=-1) {
                String seesionId = request.getRequestURL().substring(sessionIdStartPosition);
                pathBuilder.append(seesionId);
				log.log(Level.INFO, "Url rewrite found. Final redirecting path {}",pathBuilder.toString());
            }
        }
        response.sendRedirect(pathBuilder.toString());
	}

    public static boolean sendRedirectOrigin(HttpServletRequest request, HttpServletResponse response, String defaultPath) throws IOException{
        String origin = request.getParameter("origin");
		log.log(Level.INFO, "Sending redirect to orgin path {} or default {}",origin,defaultPath);
        if(origin!=null&&!origin.isEmpty()) {
			log.log(Level.INFO, "Sending redirect to orgin path {} ",origin);
            response.sendRedirect(origin);
            return true;
        }else{
			log.log(Level.INFO, "Orgin not defined. Sending redirect to default {}",defaultPath);
            sendRedirectInternal(request,response,defaultPath);
            return false;
        }
    }

    public static void prepareHtmlPage(HttpServletRequest request, HttpServletResponse response) {
		log.log(Level.INFO, "Setting up content type and charset");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
    }
    public static void prepareHtmlPage(HttpServletRequest request, HttpServletResponse response, String subHeadline) {
		log.log(Level.INFO, "Setting up page sub headline");
		prepareHtmlPage(request, response);
        request.setAttribute("subHeadline", subHeadline);
    }

    public static void prepareHtmlPage(HttpServletRequest request, HttpServletResponse response, String headline, String subHeadline) {
		log.log(Level.INFO, "Setting up page headline");
		prepareHtmlPage(request, response, subHeadline);
        request.setAttribute("headline", headline);
    }

	public static void returnJSON(HttpServletResponse response, Object object)throws IOException{
		log.log(Level.INFO, "Setting up content type and charset");
		String json = objectMapper.writeValueAsString(object);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		log.log(Level.INFO, "Converting and returning json {}", json);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}
}

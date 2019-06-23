package ua.tracker.food.controller;

import ua.tracker.food.core.security.context.SecurityContextHolder;
import ua.tracker.food.services.UserAuthenticationService;
import ua.tracker.food.services.UserAuthenticationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tracker.food.tool.ControllerTools;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private Logger log = LogManager.getLogger(LoginServlet.class);
    private UserAuthenticationService userAuthenticationService = UserAuthenticationServiceImpl.getInstance();
	
	@Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
		log.log(Level.INFO, "Trying to login with username {}",user);
        HttpSession session = request.getSession(true);
        boolean isUserLoggedOn = userAuthenticationService.login(session,user,pwd);
        if(isUserLoggedOn){
			log.log(Level.INFO, "User {} successfully logged in",user);
            ControllerTools.sendRedirectOrigin(request, response, "/");
        }else {
            ControllerTools.prepareHtmlPage(request,response);
			log.log(Level.INFO, "Wrong username or password");
            request.setAttribute("error","Wrong username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

	@Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        boolean isUserLoggedOn = SecurityContextHolder.getInstance().getSecurityData().getUser().isAuthenticated();
        if(isUserLoggedOn){
			log.log(Level.INFO, "User is already logged in");
            ControllerTools.sendRedirectInternal(request, response, "/");
        }else {
			log.log(Level.INFO, "User is not logged in. Display the login page");
            ControllerTools.prepareHtmlPage(request,response);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
}
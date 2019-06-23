package ua.tracker.food.controller;

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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private Logger log = LogManager.getLogger(LogoutServlet.class);
    private UserAuthenticationService userAuthenticationService = UserAuthenticationServiceImpl.getInstance();

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.log(Level.INFO, "Trying to logout");
        HttpSession session = request.getSession(false);
        userAuthenticationService.logout(session);
		log.log(Level.INFO, "Logout successfully completed. Sending redirect");
        ControllerTools.sendRedirectInternal(request, response, "/");
    }

}
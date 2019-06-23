package ua.tracker.food.controller;

import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.services.UserRegistrationService;
import ua.tracker.food.services.UserRegistrationServiceImpl;
import ua.tracker.food.tool.ControllerTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private Logger log = LogManager.getLogger(RegisterServlet.class);
    private UserRegistrationService userRegistrationService = UserRegistrationServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerTools.prepareHtmlPage(request,response);
        String username = ControllerTools.passParameterToAttribute(request,"login");
        String email = ControllerTools.passParameterToAttribute(request,"email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
		log.log(Level.INFO, "User is trying to register with login {} and email {}",username,email);

        try {
            userRegistrationService.registerUser(username, email, password, confirmPassword);
            request.setAttribute("success","You successfully signed up. Please try to log in");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
			log.log(Level.INFO, "User is successfully registered");
        }catch (InvalidInputException iie){
			log.error("Invalid input",iie);
            request.setAttribute("error",iie.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.log(Level.INFO, "Trying to load register form");
        ControllerTools.prepareHtmlPage(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
		log.log(Level.INFO, "Form successfully returned");
    }
}

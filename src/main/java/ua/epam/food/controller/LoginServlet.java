package ua.epam.food.controller;

import ua.epam.food.services.UserAuthenticationService;
import ua.epam.food.services.UserAuthenticationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserAuthenticationService userAuthenticationService = UserAuthenticationServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        HttpSession session = request.getSession(true);
        boolean isUserLoggedOn = userAuthenticationService.login(session,user,pwd);
        if(isUserLoggedOn){
            response.sendRedirect("LoginSuccess.jsp");
        }else {
            response.sendRedirect("/login.jsp");
        }

    }
}
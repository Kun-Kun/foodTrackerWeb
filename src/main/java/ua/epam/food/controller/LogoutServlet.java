package ua.epam.food.controller;

import ua.epam.food.services.UserAuthenticationService;
import ua.epam.food.services.UserAuthenticationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserAuthenticationService userAuthenticationService = UserAuthenticationServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        userAuthenticationService.logout(session);
        response.sendRedirect("login.jsp");
    }

}
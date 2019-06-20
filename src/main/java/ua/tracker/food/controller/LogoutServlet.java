package ua.tracker.food.controller;

import ua.tracker.food.services.UserAuthenticationService;
import ua.tracker.food.services.UserAuthenticationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import ua.tracker.food.tool.ControllerTools;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserAuthenticationService userAuthenticationService = UserAuthenticationServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        userAuthenticationService.logout(session);
        ControllerTools.sendRedirectInternal(request, response, "/");
    }

}
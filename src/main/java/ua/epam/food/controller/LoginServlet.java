package ua.epam.food.controller;

import ua.epam.food.core.security.context.SecurityContextHolder;
import ua.epam.food.services.UserAuthenticationService;
import ua.epam.food.services.UserAuthenticationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import ua.epam.food.tool.ControllerTools;

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
            ControllerTools.sendRedirectOrigin(request, response, "/");
        }else {
            ControllerTools.prepareHtmlPage(request,response);
            request.setAttribute("error","Помилка. Неправильний логін або пароль");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        boolean isUserLoggedOn = SecurityContextHolder.getInstance().getSecurityData().getUser().isAuthenticated();
        if(isUserLoggedOn){
            ControllerTools.sendRedirectInternal(request, response, "/");
        }else {
            ControllerTools.prepareHtmlPage(request,response);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
}
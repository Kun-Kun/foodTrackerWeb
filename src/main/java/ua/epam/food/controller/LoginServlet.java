package ua.epam.food.controller;

import ua.epam.food.core.security.PasswordEncoder;
import ua.epam.food.core.security.ShaPasswordEncoder;
import ua.epam.food.services.UserAuthenticationService;
import ua.epam.food.services.UserAuthenticationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
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


       /* if(userID.equals(user) && password.equals(pwd)){

            session.setAttribute("user", "Pankaj");
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            response.sendRedirect("LoginSuccess.jsp");
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }*/

    }
}
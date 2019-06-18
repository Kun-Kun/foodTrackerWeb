package ua.epam.food.controller;

import ua.epam.food.dto.ProfileSelectable;
import ua.epam.food.services.UserProfileServiceImpl;
import ua.epam.food.tool.ControllerTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserProfileServiceImpl profileService = UserProfileServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if(ControllerTools.isUserAuthenticated()) {
            Integer profileId = ControllerTools.getProfile().getId();
            ProfileSelectable profileSelectable = profileService.getProfileByProfileId(profileId);
            request.setAttribute("profile", profileSelectable);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            dispatcher.forward(request, response);
        }else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

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
import javax.servlet.http.HttpSession;
import ua.epam.food.dto.StatusJsonResponse;
import ua.epam.food.exception.InvalidInputException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserProfileServiceImpl profileService = UserProfileServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fieldName = request.getParameter("fieldName");
        String value = request.getParameter("value");
        boolean isUserLoggedOn = ControllerTools.isUserAuthenticated();
        if(isUserLoggedOn){
			try{
				Integer userId = ControllerTools.getProfile().getUserId();
				profileService.setValue(userId, fieldName, value);
				ControllerTools.returnJSON(response, new StatusJsonResponse("ok", "Value succesfully saved"));
			}catch(InvalidInputException iie){
				ControllerTools.returnJSON(response, new StatusJsonResponse("error", iie.getMessage()));
			}
        }else {
			ControllerTools.returnJSON(response, new StatusJsonResponse("error", "Unauthorized"));
        }
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

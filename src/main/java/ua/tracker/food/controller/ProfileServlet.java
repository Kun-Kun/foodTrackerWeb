package ua.tracker.food.controller;

import ua.tracker.food.dto.ProfileSelectable;
import ua.tracker.food.services.UserProfileService;
import ua.tracker.food.services.UserProfileServiceImpl;
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

import ua.tracker.food.dto.StatusJsonResponse;
import ua.tracker.food.exception.InvalidInputException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private Logger log = LogManager.getLogger(ProfileServlet.class);
    private UserProfileService profileService = UserProfileServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fieldName = request.getParameter("fieldName");
        String value = request.getParameter("value");
        try{
            Integer userId = ControllerTools.getProfile().getUserId();
			log.log(Level.INFO, "User {} is trying to edit profile field {} with valus {}",userId,fieldName,value);
            profileService.setValue(userId, fieldName, value);
            ControllerTools.returnJSON(response, new StatusJsonResponse("ok", "Value successfully saved"));
			log.log(Level.INFO, "Profile successfully edited");
        }catch(InvalidInputException iie){
			log.error("Invalid input",iie);
            ControllerTools.returnJSON(response, new StatusJsonResponse("error", iie.getMessage()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(ControllerTools.isUserAuthenticated()) {
            Integer profileId = ControllerTools.getProfile().getId();
			log.log(Level.INFO, "User is trying to load profile id {}",profileId);
            ProfileSelectable profileSelectable = profileService.getProfileSelectableByProfileId(profileId);
            request.setAttribute("profile", profileSelectable);
            ControllerTools.prepareHtmlPage(request,response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            dispatcher.forward(request, response);
			log.log(Level.INFO, "Profile successfully loaded");
        }else {
			log.log(Level.INFO, "User is not aunthenticated");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

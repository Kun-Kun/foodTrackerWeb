package ua.epam.food.controller;

import ua.epam.food.dto.ProfileSelectable;
import ua.epam.food.dto.StatusJsonResponse;
import ua.epam.food.exception.InvalidInputException;
import ua.epam.food.services.UserProfileServiceImpl;
import ua.epam.food.tool.ControllerTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/food")
public class FoodListServlet extends HttpServlet {

    private UserProfileServiceImpl profileService = UserProfileServiceImpl.getInstance();

    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fieldName = request.getParameter("fieldName");
        String value = request.getParameter("value");
        try{
            Integer userId = ControllerTools.getProfile().getUserId();
            profileService.setValue(userId, fieldName, value);
            ControllerTools.returnJSON(response, new StatusJsonResponse("ok", "Value successfully saved"));
        }catch(InvalidInputException iie){
            ControllerTools.returnJSON(response, new StatusJsonResponse("error", iie.getMessage()));
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerTools.prepareHtmlPage(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/food.jsp");
        dispatcher.forward(request, response);
    }
}

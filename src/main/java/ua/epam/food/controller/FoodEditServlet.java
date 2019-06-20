package ua.epam.food.controller;

import ua.epam.food.dto.Food;
import ua.epam.food.exception.AccessDeniedException;
import ua.epam.food.exception.InvalidInputException;
import ua.epam.food.services.FoodServiceImpl;
import ua.epam.food.services.UserProfileServiceImpl;
import ua.epam.food.tool.ControllerTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/food/edit")
public class FoodEditServlet extends HttpServlet {

    private FoodServiceImpl foodService = FoodServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = ControllerTools.passParameterToAttribute(request,"id");
        String title = ControllerTools.passParameterToAttribute(request,"title");
        String description = ControllerTools.passParameterToAttribute(request,"description");
        String fats = ControllerTools.passParameterToAttribute(request,"fats");
        String proteins = ControllerTools.passParameterToAttribute(request,"proteins");
        String carbohydrates = ControllerTools.passParameterToAttribute(request,"carbohydrates");
        String kilocalories = ControllerTools.passParameterToAttribute(request,"kilocalories");
        String weight = ControllerTools.passParameterToAttribute(request,"weight");
        Integer userId = ControllerTools.getProfile().getUserId();

        try {
            Food foodEntity = foodService.saveFood(id,title,description,fats,proteins,carbohydrates,kilocalories,weight,userId);
            ControllerTools.sendRedirectInternal(request,response, "/food/info?food="+foodEntity.getId());
        }catch (AccessDeniedException aex){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"You do not have rights to edit record");
        }catch (InvalidInputException iie){
            ControllerTools.prepareHtmlPage(request,response);
            request.setAttribute("error",iie.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodEdit.jsp");
            dispatcher.forward(request, response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerTools.prepareHtmlPage(request,response);
        String foodIdString = request.getParameter("food");
        Integer userId = ControllerTools.getProfile().getUserId();
        try {
            Food food = foodService.loadFood(foodIdString, userId);
            request.setAttribute("food", food);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodEdit.jsp");
            dispatcher.forward(request, response);
        }catch (AccessDeniedException aex){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"You do not have rights to edit record");
        }catch (InvalidInputException iie) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodEdit.jsp");
            dispatcher.forward(request, response);
        }

    }
}

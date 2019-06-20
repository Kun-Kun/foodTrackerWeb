package ua.tracker.food.controller;

import ua.tracker.food.dto.Food;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.services.FoodServiceImpl;
import ua.tracker.food.tool.ControllerTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/food/info")
public class FoodInfoServlet extends HttpServlet {

    private FoodServiceImpl foodService = FoodServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodId = request.getParameter("food");
        try {
            Food foodEntity = foodService.loadFood(foodId);
            request.setAttribute("food",foodEntity);
            ControllerTools.prepareHtmlPage(request,response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodDetail.jsp");
            dispatcher.forward(request, response);
        }catch (InvalidInputException iie){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}

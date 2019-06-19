package ua.epam.food.controller;

import ua.epam.food.dto.Food;
import ua.epam.food.services.FoodServiceImpl;
import ua.epam.food.tool.ControllerTools;

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
        Food foodEntity = foodService.loadFoodById(Integer.parseInt(foodId));
        request.setAttribute("food",foodEntity);
        ControllerTools.prepareHtmlPage(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/foodDetail.jsp");
        dispatcher.forward(request, response);
    }
}

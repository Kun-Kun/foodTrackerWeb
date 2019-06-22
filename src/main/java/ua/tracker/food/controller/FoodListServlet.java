package ua.tracker.food.controller;

import ua.tracker.food.dto.Food;
import ua.tracker.food.dto.ProfileSelectable;
import ua.tracker.food.dto.StatusJsonResponse;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.services.FoodServiceImpl;
import ua.tracker.food.services.UserProfileServiceImpl;
import ua.tracker.food.tool.ControllerTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/food")
public class FoodListServlet extends HttpServlet {

    private FoodServiceImpl foodService = FoodServiceImpl.getInstance();

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
		if(query==null){
			query = "";
		}
        ControllerTools.prepareHtmlPage(request,response);
        setFoodCardListSearchResult(request,query);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/foodList.jsp");
        dispatcher.forward(request, response);
    }

    private  void setFoodCardListSearchResult(HttpServletRequest request,String query){
        if(ControllerTools.isUserAuthenticated()){
            Integer userId = ControllerTools.getProfile().getUserId();
            List<Food> foodList = foodService.searchFood(query,userId);
            request.setAttribute("foodCards",foodList);
        }else {
            List<Food> foodList = foodService.searchFood(query);
            request.setAttribute("foodCards", foodList);
        }
    }
}

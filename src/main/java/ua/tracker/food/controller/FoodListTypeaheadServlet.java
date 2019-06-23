package ua.tracker.food.controller;

import ua.tracker.food.dto.Food;
import ua.tracker.food.services.FoodServiceImpl;
import ua.tracker.food.tool.ControllerTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tracker.food.services.FoodService;

@WebServlet("/food/search")
public class FoodListTypeaheadServlet extends HttpServlet {
	private Logger log = LogManager.getLogger(FoodListTypeaheadServlet.class);
    private FoodService foodService = FoodServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Food> foodList = searchFood(query);
        ControllerTools.returnJSON(response,foodList);
		log.log(Level.INFO, "Food list successfully returned");
    }

    private  List<Food> searchFood(String query){
        if(ControllerTools.isUserAuthenticated()){
            Integer userId = ControllerTools.getProfile().getUserId();
			log.log(Level.INFO, "User {} is trying to search food list with query '{}'",userId,query);
            return foodService.searchFood(query,userId);
        }else {
			log.log(Level.INFO, "Unknown user is trying to search food list with query '{}'",query);
            return foodService.searchFood(query);
        }
    }
}

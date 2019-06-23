package ua.tracker.food.controller;

import ua.tracker.food.dto.Food;
import ua.tracker.food.exception.AccessDeniedException;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tracker.food.services.FoodService;

@WebServlet("/food/delete")
public class FoodDeleteServlet extends HttpServlet {
	
	private Logger log = LogManager.getLogger(FoodDeleteServlet.class);
    private FoodService foodService = FoodServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = ControllerTools.passParameterToAttribute(request,"id");
        Integer userId = ControllerTools.getProfile().getUserId();
		log.log(Level.INFO, "User with id {} is trying to delete food with id {}",userId,id);
        try {
            Food foodEntity = foodService.deleteFood(id,userId);
            ControllerTools.sendRedirectInternal(request,response, "/food");
			log.log(Level.INFO, "Food successfully deleted");
        }catch (AccessDeniedException aex){
			log.error("User not allowed to delete record",aex);
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"You do not have rights to edit record");
        }catch (InvalidInputException iie){
			log.error("Invalid input",iie);
            ControllerTools.prepareHtmlPage(request,response);
            request.setAttribute("error",iie.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodDetail.jsp");
            dispatcher.forward(request, response);
        }


    }

}

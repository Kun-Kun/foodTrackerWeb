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

@WebServlet("/food/edit")
public class FoodEditServlet extends HttpServlet {
	private Logger log = LogManager.getLogger(FoodEditServlet.class);
    private FoodService foodService = FoodServiceImpl.getInstance();

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
		log.log(Level.INFO, "User {} is trying to edit food id {}",userId,id);

        try {
            Food foodEntity = foodService.saveFood(id,title,description,fats,proteins,carbohydrates,kilocalories,weight,userId);
            ControllerTools.sendRedirectInternal(request,response, "/food/info?food="+foodEntity.getId());
			log.log(Level.INFO, "Food successfully edited");
        }catch (AccessDeniedException aex){
			log.error("User not allowed to edit record",aex);
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"You do not have rights to edit record");
        }catch (InvalidInputException iie){
			log.error("Invalid input",iie);
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
		log.log(Level.INFO, "User {} is trying to load food editing form with food id {}",userId,foodIdString);
        try {
            Food food = foodService.loadFood(foodIdString, userId);
            request.setAttribute("food", food);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodEdit.jsp");
            dispatcher.forward(request, response);
			log.log(Level.INFO, "Food form successfully returned");
        }catch (AccessDeniedException aex){
			log.error("User not allowed to edit record",aex);
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"You do not have rights to edit record");
        }catch (InvalidInputException iie) {
			log.error("Invalid input",iie);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodEdit.jsp");
            dispatcher.forward(request, response);
        }

    }
}

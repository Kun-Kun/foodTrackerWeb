package ua.tracker.food.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tracker.food.dto.Food;
import ua.tracker.food.exception.AccessDeniedException;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.services.FoodService;
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
    private Logger log = LogManager.getLogger(FoodInfoServlet.class);
    private FoodService foodService = FoodServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodId = request.getParameter("food");
        log.log(Level.INFO, "User is trying to load food page id {}", foodId);
        try {
            ControllerTools.prepareHtmlPage(request, response);
            Food foodEntity = null;
            if (ControllerTools.isUserAuthenticated()) {
                Integer userId = ControllerTools.getProfile().getUserId();
                foodEntity = foodService.loadFood(foodId, userId);
            } else {
                foodEntity = foodService.loadFood(foodId);
            }

            request.setAttribute("food", foodEntity);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodDetail.jsp");
            dispatcher.forward(request, response);
            log.log(Level.INFO, "Food successfully returned");
        } catch (InvalidInputException iie) {
            log.error("Invalid input", iie);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }catch (AccessDeniedException ade) {
            log.error("Invalid input", ade);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}

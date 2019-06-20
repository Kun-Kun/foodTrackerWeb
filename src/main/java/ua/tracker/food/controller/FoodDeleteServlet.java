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

@WebServlet("/food/delete")
public class FoodDeleteServlet extends HttpServlet {

    private FoodServiceImpl foodService = FoodServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = ControllerTools.passParameterToAttribute(request,"id");
        Integer userId = ControllerTools.getProfile().getUserId();

        try {
            Food foodEntity = foodService.deleteFood(id,userId);
            ControllerTools.sendRedirectInternal(request,response, "/food");
        }catch (AccessDeniedException aex){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"You do not have rights to edit record");
        }catch (InvalidInputException iie){
            ControllerTools.prepareHtmlPage(request,response);
            request.setAttribute("error",iie.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/foodDetail.jsp");
            dispatcher.forward(request, response);
        }


    }

}

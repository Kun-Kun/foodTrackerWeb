package ua.tracker.food.controller;

import ua.tracker.food.dto.EatenTrackerRecord;
import ua.tracker.food.dto.Food;
import ua.tracker.food.dto.StatusJsonResponse;
import ua.tracker.food.exception.AccessDeniedException;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.services.TrackerServiceImpl;
import ua.tracker.food.tool.ControllerTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tracker/eaten")
public class TrackerEatenAddServlet extends HttpServlet {

    private TrackerServiceImpl trackerService = TrackerServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dateString = request.getParameter("dateString");
        String foodId = request.getParameter("foodId");
        String amount = request.getParameter("amount");
        String repastTypeId = request.getParameter("repastTypeId");
        Integer userId = ControllerTools.getProfile().getUserId();
        trackerService.addEatenFood(dateString,foodId,amount,userId,repastTypeId);
        ControllerTools.returnJSON(response, new StatusJsonResponse("ok", "Food successfully added"));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String repast = request.getParameter("repast");
        Integer user = ControllerTools.getProfile().getUserId();
        List<EatenTrackerRecord> result = trackerService.loadEatenFoodList(date, user, repast);
        ControllerTools.returnJSON(response, result);
    }

}

package ua.tracker.food.controller;

import ua.tracker.food.dto.EatenTrackerRecord;
import ua.tracker.food.dto.StatusJsonResponse;
import ua.tracker.food.services.TrackerServiceImpl;
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
import ua.tracker.food.services.TrackerService;

@WebServlet("/tracker/eaten")
public class TrackerEatenAddServlet extends HttpServlet {
	private Logger log = LogManager.getLogger(TrackerEatenAddServlet.class);
    private TrackerService trackerService = TrackerServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dateString = request.getParameter("dateString");
        String foodId = request.getParameter("foodId");
        String amount = request.getParameter("amount");
        String repastTypeId = request.getParameter("repastTypeId");
        Integer userId = ControllerTools.getProfile().getUserId();
		log.log(Level.INFO, "User {} is trying to add food id {}, {}g. Date {} , repast type {}.",userId,foodId,amount,dateString,repastTypeId);
        trackerService.addEatenFood(dateString,foodId,amount,userId,repastTypeId);
        ControllerTools.returnJSON(response, new StatusJsonResponse("ok", "Food successfully added"));
		log.log(Level.INFO, "Food successfully  added");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String repastType =  request.getParameter("repast");
        Integer userId = ControllerTools.getProfile().getUserId();
		log.log(Level.INFO, "User {} is trying to load eaten food at {} , repast type {}",userId,date,repastType);
        List<EatenTrackerRecord> result = trackerService.loadEatenFoodList(date, userId, repastType);
		log.log(Level.INFO, "Food list successfully returned");
        ControllerTools.returnJSON(response, result);
    }

}

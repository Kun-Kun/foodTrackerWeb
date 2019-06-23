package ua.tracker.food.controller;

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

@WebServlet("/tracker")
public class TrackerServlet extends HttpServlet {
	private Logger log = LogManager.getLogger(TrackerServlet.class);
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.log(Level.INFO, "Trying to load tracker page");
        ControllerTools.prepareHtmlPage(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tracker.jsp");
        dispatcher.forward(request, response);
		log.log(Level.INFO, "Page returned");

    }

}

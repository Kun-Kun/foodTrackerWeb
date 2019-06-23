/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.tracker.food.services;

import java.util.List;
import ua.tracker.food.dto.EatenTrackerRecord;

/**
 *
 * @author nmcdo5
 */
public interface TrackerService {
	void addEatenFood(String dateString, String foodId, String amountString, Integer userId, String repastTypeId);
	List<EatenTrackerRecord> loadEatenFoodList(String dateString,Integer userId, String repastTypeId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.tracker.food.services;

import java.util.Date;
import ua.tracker.food.dao.entity.DailyGoalEntity;

/**
 *
 * @author nmcdo5
 */
public interface CaloriesCalculationService {
	DailyGoalEntity createNewDailyGoalEntity(Date date, Integer userId);
}

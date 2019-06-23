/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.tracker.food.services;

import java.util.List;
import ua.tracker.food.dto.Food;
import ua.tracker.food.exception.AccessDeniedException;
import ua.tracker.food.exception.InvalidInputException;

/**
 *
 * @author nmcdo5
 */
public interface FoodService {
	List<Food> searchFood(String text, Integer userId);
	List<Food> searchFood(String text);
	Food loadFood(String id) throws InvalidInputException;
	Food saveFood(String id, String title, String description, String fats, String proteins, String carbohydrates, String kilocalories, String weight, Integer userId) throws InvalidInputException, AccessDeniedException;
	Food loadFood(String id, Integer userId) throws InvalidInputException, AccessDeniedException ;
	Food deleteFood(String id, Integer userId) throws InvalidInputException, AccessDeniedException;
}

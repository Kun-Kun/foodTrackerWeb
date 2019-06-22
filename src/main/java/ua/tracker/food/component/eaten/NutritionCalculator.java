package ua.tracker.food.component.eaten;

import ua.tracker.food.component.Nutrition;

import java.util.List;

public interface NutritionCalculator {
    Nutrition calculateEatenNutrition(List<Edible> breakfast, List<Edible> lunch, List<Edible> dinner, List<Edible> snack );
}

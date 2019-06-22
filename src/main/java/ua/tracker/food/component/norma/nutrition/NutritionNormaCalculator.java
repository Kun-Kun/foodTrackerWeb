package ua.tracker.food.component.norma.nutrition;

import ua.tracker.food.component.NutrientProportion;
import ua.tracker.food.component.Nutrition;
import ua.tracker.food.component.UserParameters;


public interface NutritionNormaCalculator {
    Nutrition calculateNorma(UserParameters parameters, NutrientProportion proportion,float goalCoefficient, float physicalLoadLevelCoefficient);
}

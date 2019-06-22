package ua.tracker.food.component.norma.calories;

import ua.tracker.food.component.UserParameters;

public interface CaloriesNormCalculator {
    float calculateNorm(UserParameters userParameters, float goalCoefficient, float physicalLoadLevelCoefficient);
}

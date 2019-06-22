package ua.tracker.food.component.norma.calories;

import ua.tracker.food.component.UserParameters;

public interface BasalMetabolicRateCalculator {
    float calculateBasalMetabolicRate(UserParameters parameters);
}

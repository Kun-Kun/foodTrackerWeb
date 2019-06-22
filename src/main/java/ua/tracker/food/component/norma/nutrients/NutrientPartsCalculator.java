package ua.tracker.food.component.norma.nutrients;

import ua.tracker.food.component.NutrientProportion;
import ua.tracker.food.component.NutrientValues;

public interface NutrientPartsCalculator {
    NutrientValues calculatePartsInCalories(float calories, NutrientProportion proportion);
    NutrientValues calculatePartsInGram(float calories, NutrientProportion proportion);
}

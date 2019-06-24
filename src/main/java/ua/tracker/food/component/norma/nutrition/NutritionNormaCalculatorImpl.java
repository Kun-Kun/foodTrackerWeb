package ua.tracker.food.component.norma.nutrition;

import ua.tracker.food.component.*;
import ua.tracker.food.component.norma.calories.*;
import ua.tracker.food.component.norma.nutrients.NutrientPartsCalculator;
import ua.tracker.food.component.norma.nutrients.NutrientPartsCalculatorImpl;

public class NutritionNormaCalculatorImpl implements NutritionNormaCalculator{
    private CaloriesNormCalculator caloriesNormCalculator;
    private RepastPortionCalculator repastPortionCalculator;
    private NutrientPartsCalculator nutrientPartsCalculator;

    public NutritionNormaCalculatorImpl(CaloriesNormCalculator caloriesNormCalculator, RepastPortionCalculator repastPortionCalculator, NutrientPartsCalculator nutrientPartsCalculator) {
        this.caloriesNormCalculator = caloriesNormCalculator;
        this.repastPortionCalculator = repastPortionCalculator;
        this.nutrientPartsCalculator = nutrientPartsCalculator;
    }


    @Override
    public Nutrition calculateNorma(UserParameters parameters, NutrientProportion proportion, float goalCoefficient, float physicalLoadLevelCoefficient) {
        float dailyKilocalories = caloriesNormCalculator.calculateNorm(parameters,goalCoefficient,physicalLoadLevelCoefficient);
        RepastPortion portionAllocation  = repastPortionCalculator.calculatePortion(dailyKilocalories);
        NutrientValues nutrientAllocation = nutrientPartsCalculator.calculatePartsInGram(dailyKilocalories,proportion);

        Nutrition nutritionNorma = new Nutrition();
        nutritionNorma.setDailyKilocalories(dailyKilocalories);
        nutritionNorma.setNutrientAllocation(nutrientAllocation);
        nutritionNorma.setPortionAllocation(portionAllocation);
        return nutritionNorma;
    }

}

package ua.tracker.food.component.norma.nutrition;

import org.junit.Before;
import org.junit.Test;
import ua.tracker.food.component.Gender;
import ua.tracker.food.component.NutrientProportion;
import ua.tracker.food.component.Nutrition;
import ua.tracker.food.component.UserParameters;
import ua.tracker.food.component.norma.calories.CaloriesNormCalculatorImpl;
import ua.tracker.food.component.norma.calories.HarrisBenedictBasalMetabolicRateCalculator;
import ua.tracker.food.component.norma.calories.RepastPortionCalculator;
import ua.tracker.food.component.norma.calories.RepastPortionCalculatorImpl;
import ua.tracker.food.component.norma.nutrients.NutrientPartsCalculator;
import ua.tracker.food.component.norma.nutrients.NutrientPartsCalculatorImpl;

import static org.junit.Assert.assertEquals;

public class NutritionNormaCalculatorImplTest {

    private CaloriesNormCalculatorImpl caloriesNormCalculator;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void calculateNorma() throws Exception {
        caloriesNormCalculator = new CaloriesNormCalculatorImpl(new HarrisBenedictBasalMetabolicRateCalculator());
        RepastPortionCalculator repastPortionCalculator = new RepastPortionCalculatorImpl();
        NutrientPartsCalculator nutrientPartsCalculator = new NutrientPartsCalculatorImpl();
        NutritionNormaCalculatorImpl nutritionNormaCalculator = new NutritionNormaCalculatorImpl(caloriesNormCalculator, repastPortionCalculator, nutrientPartsCalculator);
        UserParameters userParameters = new UserParameters();
        userParameters.setWeight(94);
        userParameters.setHeight(194);
        userParameters.setAge(26);
        userParameters.setGender(Gender.MALE);
        NutrientProportion nutrientProportion = new NutrientProportion();
        nutrientProportion.setCarbohydrates(0.5f);
        nutrientProportion.setFats(0.3f);
        nutrientProportion.setProteins(0.2f);
        Nutrition nutrition = nutritionNormaCalculator.calculateNorma(userParameters, nutrientProportion, 1.2f, 1.3f);

        assertEquals(3360.1, nutrition.getDailyKilocalories(), 0.1);

        assertEquals(168, nutrition.getPortionAllocation().getSnackPortion(), 0.1);
        assertEquals(1344, nutrition.getPortionAllocation().getLunchPortion(), 0.1);
        assertEquals(840, nutrition.getPortionAllocation().getDinnerPortion(), 0.1);
        assertEquals(1008, nutrition.getPortionAllocation().getBreakfastPortion(), 0.1);

        assertEquals(168, nutrition.getNutrientAllocation().getProteins(), 0.1);
        assertEquals(420, nutrition.getNutrientAllocation().getCarbohydrates(), 0.1);
        assertEquals(112, nutrition.getNutrientAllocation().getFats(), 0.1);

    }


}
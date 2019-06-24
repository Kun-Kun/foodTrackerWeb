package ua.tracker.food.component.eaten;

import org.junit.Before;
import org.junit.Test;
import ua.tracker.food.component.NutrientValues;
import ua.tracker.food.component.Nutrition;
import ua.tracker.food.component.RepastPortion;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NutritionCalculatorImplTest {
    private NutritionCalculatorImpl nutritionCalculator;
    List<Edible> edibles;
    @Before
    public void setUp() throws Exception {
        nutritionCalculator = new NutritionCalculatorImpl();
        edibles = new ArrayList<>();
        edibles.add(new TestEdible(17,2,13,16));
        edibles.add(new TestEdible(22,22,16,8));
        edibles.add(new TestEdible(7,45,17,6));
        edibles.add(new TestEdible(10,1,19,10));
    }

    @Test
    public void calculateEatenNutrition() throws Exception {
        Nutrition nutrition = nutritionCalculator.calculateEatenNutrition(edibles,edibles,edibles,edibles);
        assertEquals(160,nutrition.getDailyKilocalories(),0.1);
        assertEquals(40,nutrition.getPortionAllocation().getBreakfastPortion(),0.1);
        assertEquals(40,nutrition.getPortionAllocation().getDinnerPortion(),0.1);
        assertEquals(40,nutrition.getPortionAllocation().getLunchPortion(),0.1);
        assertEquals(40,nutrition.getPortionAllocation().getSnackPortion(),0.1);
    }

    @Test
    public void summarizeEatenPortionFats() throws Exception {
        float fats = nutritionCalculator.summarizeEatenPortion(edibles,Edible::getFats);
        assertEquals(56,fats,0.1);
    }

    @Test
    public void summarizeEatenPortionProteins() throws Exception {
        float proteins = nutritionCalculator.summarizeEatenPortion(edibles,Edible::getProteins);
        assertEquals(70,proteins,0.1);
    }

    @Test
    public void summarizeEatenPortion() throws Exception {
        float carbohydrates = nutritionCalculator.summarizeEatenPortion(edibles,Edible::getCarbohydrates);
        assertEquals(65,carbohydrates,0.1);
    }


    @Test
    public void calculateKilocalories() throws Exception {
        float kilocalories = nutritionCalculator.calculateKilocalories(edibles);
        assertEquals(40,kilocalories,0.1);
    }


    @Test
    public void calculateNutrientAllocation() throws Exception {
        NutrientValues nutrientValues  = nutritionCalculator.calculateNutrientAllocation(edibles,edibles,edibles,edibles);
        assertEquals(224,nutrientValues.getFats(),0.1);
        assertEquals(260,nutrientValues.getCarbohydrates(),0.1);
        assertEquals(280,nutrientValues.getProteins(),0.1);
    }

    private class TestEdible implements Edible {
        private float fats;
        private float proteins;
        private float carbohydrates;
        private float kilocalories;

        public TestEdible(float fats, float proteins, float carbohydrates, float kilocalories) {
            this.fats = fats;
            this.proteins = proteins;
            this.carbohydrates = carbohydrates;
            this.kilocalories = kilocalories;
        }

        @Override
        public float getFats() {
            return fats;
        }

        @Override
        public float getProteins() {
            return proteins;
        }

        @Override
        public float getCarbohydrates() {
            return carbohydrates;
        }

        @Override
        public float getKilocalories() {
            return kilocalories;
        }
    }

}
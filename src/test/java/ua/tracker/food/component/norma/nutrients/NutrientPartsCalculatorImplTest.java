package ua.tracker.food.component.norma.nutrients;

import org.junit.Before;
import org.junit.Test;
import ua.tracker.food.component.NutrientProportion;
import ua.tracker.food.component.NutrientValues;
import ua.tracker.food.component.norma.calories.CaloriesNormCalculatorImpl;

import static org.junit.Assert.*;

public class NutrientPartsCalculatorImplTest {

    private NutrientPartsCalculatorImpl nutrientPartsCalculator;

    @Before
    public void setUp() throws Exception {
        nutrientPartsCalculator = new NutrientPartsCalculatorImpl();
    }

    @Test
    public void calculatePartsInCalories() throws Exception {
        NutrientProportion proportion = new NutrientProportion(0.3f,0.3f,0.4f);
        NutrientValues values = nutrientPartsCalculator.calculatePartsInCalories(1000,proportion);
        assertEquals(300,values.getFats(),0.1f);
        assertEquals(300,values.getCarbohydrates(),0.1f);
        assertEquals(400,values.getProteins(),0.1f);
    }



    @Test
    public void calculatePartsInGram() throws Exception {
        NutrientProportion proportion = new NutrientProportion(0.3f,0.3f,0.4f);
        NutrientValues values = nutrientPartsCalculator.calculatePartsInGram(1000,proportion);
        assertEquals(33.33,values.getFats(),0.1f);
        assertEquals(75f,values.getCarbohydrates(),0.1f);
        assertEquals(100,values.getProteins(),0.1f);
    }

}
package ua.tracker.food.component.norma.calories;

import org.junit.Before;
import org.junit.Test;
import ua.tracker.food.component.UserParameters;

import static org.junit.Assert.*;

public class CaloriesNormCalculatorImplTest {

    private CaloriesNormCalculatorImpl caloriesNormCalculator;

    @Before
    public void setUp() throws Exception {
        caloriesNormCalculator = new CaloriesNormCalculatorImpl(parameters -> 100);
    }

    @Test
    public void calculateNorm() throws Exception {
        float result = caloriesNormCalculator.calculateNorm(new UserParameters(),1,1);
        float expected = 100;
        assertEquals(expected,result,0.001f);
    }

    @Test
    public void calculateNorm1() throws Exception {
        float result = caloriesNormCalculator.calculateNorm(new UserParameters(),1,1.2f);
        float expected = 120;
        assertEquals(expected,result,0.001f);
    }

    @Test
    public void calculateNorm2() throws Exception {
        float result = caloriesNormCalculator.calculateNorm(new UserParameters(),1.3f,1);
        float expected = 130;
        assertEquals(expected,result,0.001f);
    }

}
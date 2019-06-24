package ua.tracker.food.component.norma.calories;

import org.junit.Before;
import org.junit.Test;
import ua.tracker.food.component.RepastPortion;

import static org.junit.Assert.*;

public class RepastPortionCalculatorImplTest {


    @Test
    public void calculatePortion() throws Exception {
        RepastPortionCalculatorImpl portionCalculator = new RepastPortionCalculatorImpl();
        RepastPortion portion = portionCalculator.calculatePortion(1000);

        assertEquals(300,portion.getBreakfastPortion(),0.1);
        assertEquals(250,portion.getDinnerPortion(),0.1);
        assertEquals(400,portion.getLunchPortion(),0.1);
        assertEquals(50,portion.getSnackPortion(),0.1);
    }

    @Test
    public void calculateTotal() throws Exception {
        RepastPortionCalculatorImpl portionCalculator = new RepastPortionCalculatorImpl();
        RepastPortion portion = portionCalculator.calculatePortion(1000);
        float total = portion.getBreakfastPortion()+portion.getDinnerPortion()+portion.getLunchPortion()+portion.getSnackPortion();
        assertEquals(1000,total,0.1);

    }

}
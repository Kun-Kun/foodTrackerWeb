package ua.tracker.food.component.norma.calories;

import ua.tracker.food.component.RepastPortion;

public class RepastPortionCalculatorImpl implements RepastPortionCalculator {

    private final static float BREAKFAST_PORTION = 0.3f;
    private final static float LUNCH_PORTION = 0.4f;
    private final static float DINNER_PORTION = 0.25f;
    private final static float SNACK_PORTION = 0.05f;


    @Override
    public RepastPortion calculatePortion(float total) {
        RepastPortion repastPortion = new RepastPortion();
        repastPortion.setBreakfastPortion(BREAKFAST_PORTION*total);
        repastPortion.setLunchPortion(LUNCH_PORTION*total);
        repastPortion.setDinnerPortion(DINNER_PORTION*total);
        repastPortion.setSnackPortion(SNACK_PORTION*total);
        return repastPortion;
    }
}

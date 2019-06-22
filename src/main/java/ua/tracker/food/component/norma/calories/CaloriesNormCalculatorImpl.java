package ua.tracker.food.component.norma.calories;

import ua.tracker.food.component.UserParameters;

public class CaloriesNormCalculatorImpl implements CaloriesNormCalculator {

    private BasalMetabolicRateCalculator basalMetabolicRateCalculator;

    public CaloriesNormCalculatorImpl(BasalMetabolicRateCalculator basalMetabolicRateCalculator) {
        this.basalMetabolicRateCalculator = basalMetabolicRateCalculator;
    }

    @Override
    public float calculateNorm(UserParameters userParameters, float goalCoefficient, float physicalLoadLevelCoefficient) {
        float basalMetabolicRate = basalMetabolicRateCalculator.calculateBasalMetabolicRate(userParameters);
        return  basalMetabolicRate * goalCoefficient * physicalLoadLevelCoefficient;
    }


}

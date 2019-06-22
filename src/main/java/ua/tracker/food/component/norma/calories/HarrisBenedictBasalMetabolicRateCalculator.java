package ua.tracker.food.component.norma.calories;

import ua.tracker.food.component.UserParameters;

public class HarrisBenedictBasalMetabolicRateCalculator implements BasalMetabolicRateCalculator {

    @Override
    public float calculateBasalMetabolicRate(UserParameters parameters) {
        switch (parameters.getGender()){
            case DEFAULT:
                return calculateForDefault(parameters);
            case MALE:
                return calculateForMale(parameters);
            case FEMALE:
                return calculateForFemale(parameters);
        }
        return 0;
    }

    private float calculationFormula(float baseCoefficient,float weightCoefficient,float heightCoefficient,float ageCoefficient, UserParameters user ){
        return baseCoefficient + ( weightCoefficient * user.getWeight() ) + ( heightCoefficient * user.getHeight() ) - ( ageCoefficient *user.getAge() );
    }

    private float calculateForMale(UserParameters parameters){
        return calculationFormula(66.5f,13.75f,5.003f,6.755f,parameters);
    }

    private float calculateForFemale(UserParameters parameters){
        return calculationFormula(655.1f,9.563f,1.850f,4.676f,parameters);
    }

    private float calculateForDefault(UserParameters parameters){
        return calculationFormula(360.5f,11.65f,3.426f,5.715f,parameters);
    }
}

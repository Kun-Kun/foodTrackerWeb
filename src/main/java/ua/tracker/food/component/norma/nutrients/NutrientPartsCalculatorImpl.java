package ua.tracker.food.component.norma.nutrients;

import ua.tracker.food.component.NutrientProportion;
import ua.tracker.food.component.NutrientValues;

public class NutrientPartsCalculatorImpl implements NutrientPartsCalculator {

    private final static int KILOCALORIES_PER_PROTEIN_1G = 4;
    private final static int KILOCALORIES_PER_FAT_1G = 9;
    private final static int KILOCALORIES_PER_CARBOHYDRATE_1G = 4;

    @Override
    public NutrientValues calculatePartsInCalories(float calories, NutrientProportion proportion) {
        NutrientValues nutrients = new NutrientValues();
        nutrients.setFats(calories*proportion.getFats());
        nutrients.setCarbohydrates(calories*proportion.getCarbohydrates());
        nutrients.setProteins(calories*proportion.getProteins());
        return nutrients;
    }

    @Override
    public NutrientValues calculatePartsInGram(float calories, NutrientProportion proportion) {
        NutrientValues nutrientsCalories = calculatePartsInCalories(calories,proportion);
        NutrientValues nutrientsInGram = new NutrientValues();
        nutrientsInGram.setProteins(nutrientsCalories.getProteins()/KILOCALORIES_PER_PROTEIN_1G);
        nutrientsInGram.setFats(nutrientsCalories.getFats()/KILOCALORIES_PER_FAT_1G);
        nutrientsInGram.setCarbohydrates(nutrientsCalories.getCarbohydrates()/KILOCALORIES_PER_CARBOHYDRATE_1G);
        return nutrientsInGram;
    }
}

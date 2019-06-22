package ua.tracker.food.component.eaten;

import com.google.common.collect.Streams;
import ua.tracker.food.component.NutrientValues;
import ua.tracker.food.component.Nutrition;
import ua.tracker.food.component.RepastPortion;

import java.util.List;
import java.util.function.Function;

public class NutritionCalculatorImpl implements NutritionCalculator {

    @Override
    public Nutrition calculateEatenNutrition(List<Edible> breakfast, List<Edible> lunch, List<Edible> dinner, List<Edible> snack) {
        Nutrition nutrition = new Nutrition();
        float dailyKilocalories = summarizeEatenPortions(breakfast, lunch, dinner, snack, Edible::getKilocalories);
        RepastPortion repastPortion = calculateRepastPortion(breakfast,lunch,dinner,snack);
        NutrientValues nutrientValues = calculateNutrientAllocation(breakfast, lunch, dinner, snack);
        
        nutrition.setDailyKilocalories(dailyKilocalories);
        nutrition.setPortionAllocation(repastPortion);
        nutrition.setNutrientAllocation(nutrientValues);
        return nutrition;
    }

    private float summarizeEatenPortion(List<Edible> list, Function<Edible, Float> mapper){
        return list
                .stream()
                .map(mapper)
                .reduce(0f, (value1, value2) -> value1+value2)
                .floatValue();
    }

    private float summarizeEatenPortions(List<Edible> breakfast, List<Edible> lunch, List<Edible> dinner, List<Edible> snack, Function<Edible, Float> mapper){
        return Streams.concat(lunch.stream(),breakfast.stream(),dinner.stream(),snack.stream())
                .map(mapper)
                .reduce(0f, (value1, value2) -> value1+value2)
                .floatValue();
    }

    private float calculateKilocalories(List<Edible> list){
        return summarizeEatenPortion(list,Edible::getKilocalories);
    }

    private RepastPortion calculateRepastPortion(List<Edible> breakfast, List<Edible> lunch, List<Edible> dinner, List<Edible> snack){
        RepastPortion repastPortion = new RepastPortion();

        float breakfastPortion = calculateKilocalories(breakfast);
        float lunchPortion = calculateKilocalories(lunch);
        float dinnerPortion = calculateKilocalories(dinner);
        float snackPortion = calculateKilocalories(snack);

        repastPortion.setBreakfastPortion(breakfastPortion);
        repastPortion.setLunchPortion(lunchPortion);
        repastPortion.setDinnerPortion(dinnerPortion);
        repastPortion.setSnackPortion(snackPortion);
        return repastPortion;
    }
    
    private NutrientValues calculateNutrientAllocation(List<Edible> breakfast, List<Edible> lunch, List<Edible> dinner, List<Edible> snack){
        NutrientValues dailyNutrientsAllocation = new NutrientValues();
        summarizeEatenPortions(breakfast, lunch, dinner, snack, Edible::getKilocalories);
        
        float dailyCarbohydrates = summarizeEatenPortions(breakfast, lunch, dinner, snack, Edible::getCarbohydrates);
        float dailyProteins = summarizeEatenPortions(breakfast, lunch, dinner, snack, Edible::getProteins);
        float dailyFats = summarizeEatenPortions(breakfast, lunch, dinner, snack, Edible::getFats);

        dailyNutrientsAllocation.setCarbohydrates(dailyCarbohydrates);
        dailyNutrientsAllocation.setFats(dailyFats);
        dailyNutrientsAllocation.setProteins(dailyProteins);
        return dailyNutrientsAllocation;
    }

}

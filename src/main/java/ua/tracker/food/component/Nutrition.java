package ua.tracker.food.component;

import lombok.Data;

@Data
public class Nutrition {
    private float dailyKilocalories;
    private RepastPortion portionAllocation;
    private NutrientValues nutrientAllocation;

}

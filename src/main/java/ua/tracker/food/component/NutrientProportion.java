package ua.tracker.food.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutrientProportion {
    private float fats;
    private float carbohydrates;
    private float proteins;
}

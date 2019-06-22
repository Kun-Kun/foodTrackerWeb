package ua.tracker.food.dto;

import lombok.Data;
import ua.tracker.food.component.RepastType;

import java.util.Date;

@Data
public class EatenTrackerRecord {
    private Integer id;
    private Integer foodId;
    private RepastType repastType;
    private String imageUrl;
    private String title;
    private String description;
    private Float portionWeight;
    private Float fats;
    private Float proteins;
    private Float carbohydrates;
    private Float kilocalories;

}

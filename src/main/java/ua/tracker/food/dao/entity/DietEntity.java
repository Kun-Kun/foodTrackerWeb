package ua.tracker.food.dao.entity;

import lombok.Data;

@Data
public class DietEntity {
    private Integer id;
    private String name;
    private String description;
    private Float proteinCoefficient;
    private Float fatCoefficient;
    private Float carbohydrateCoefficient;
}

package ua.tracker.food.dao.entity;

import lombok.Data;

@Data
public class SexEntity {
    private Integer id;
    private String name;
    private String description;
    private Float calorieBase;
    private Float weightCoefficient;
    private Float heightCoefficient;
    private Float ageCoefficient;
}

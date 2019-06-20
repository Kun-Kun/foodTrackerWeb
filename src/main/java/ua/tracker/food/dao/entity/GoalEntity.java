package ua.tracker.food.dao.entity;

import lombok.Data;

@Data
public class GoalEntity {
    private Integer id;
    private String name;
    private String description;
    private Float coefficient;
}

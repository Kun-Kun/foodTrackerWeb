package ua.epam.food.dao.entity;

import lombok.Data;

@Data
public class PhysicalLoadLevelEntity {
    private Integer id;
    private String name;
    private String description;
    private Float coefficient;
}

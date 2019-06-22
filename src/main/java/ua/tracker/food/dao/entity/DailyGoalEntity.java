package ua.tracker.food.dao.entity;

import lombok.Data;
import ua.tracker.food.component.Gender;
import ua.tracker.food.component.RepastType;

import java.util.Date;

@Data
public class DailyGoalEntity {

    private Integer id;
    private Integer userId;
    private Date eventDate;
    private Integer dietId;
    private Float kilocalories;

}

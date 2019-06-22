package ua.tracker.food.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ua.tracker.food.component.RepastType;

import java.util.Date;

@Data
public class EatenRecordEntity {

    private Integer id;
    private Integer dailyGoalId;
    private Integer foodId;
    private RepastType repastType;
    private Float portionWeight;
    private Float fats;
    private Float proteins;
    private Float carbohydrates;
    private Float kilocalories;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date changeDate;

}

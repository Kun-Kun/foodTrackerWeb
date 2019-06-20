package ua.epam.food.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FoodEntity {
    private Integer id;
    private Integer galleryId;
    private String title;
    private String description;
    private Float portionWeight;
    private Float fats;
    private Float proteins;
    private Float carbohydrates;
    private Float kilocalories;
    private Integer creatorId;
    private Boolean defaultRecord;
    private Boolean deleted;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;


}

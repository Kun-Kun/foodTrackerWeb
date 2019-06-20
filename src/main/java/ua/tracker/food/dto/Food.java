package ua.tracker.food.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Food {

    private Integer id;
    private List<String> imageUrls;
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

package ua.tracker.food.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ua.tracker.food.component.Gender;

import java.util.Date;

@Data
public class ProfileEntity {

    private Integer id;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Integer goalId;
    private Integer dietId;
    private Integer physicalLoadLevelId;
    private Integer height;
    private Float weight;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

}

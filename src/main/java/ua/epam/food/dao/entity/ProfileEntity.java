package ua.epam.food.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
public class ProfileEntity {

    private Integer id;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer sexId;
    private Integer goalId;
    private Integer dietId;
    private Integer physicalLoadLevelId;
    private Integer height;
    private Float weight;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

}

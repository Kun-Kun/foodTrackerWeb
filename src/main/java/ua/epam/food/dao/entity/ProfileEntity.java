package ua.epam.food.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ua.epam.food.core.db.mapper.JsonDateSerializer;

import java.util.Date;

@Data
public class ProfileEntity {

    private Integer id;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer goalId;
    private Integer dietId;
    private Integer height;
    private Float weight;

    //Todo test annotations
    //@JsonSerialize(using=JsonDateSerializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date birthday;

}

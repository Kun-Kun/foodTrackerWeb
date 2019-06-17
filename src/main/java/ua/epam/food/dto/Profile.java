package ua.epam.food.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Profile {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer goalId;
    private Integer dietId;
    private Integer height;
    private Float weight;
    private Date birthday;
}

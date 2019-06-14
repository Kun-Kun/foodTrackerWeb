package ua.epam.food.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Profile {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer goal;
    private Integer height;
    private Float weight;
    private Date birthday;
}

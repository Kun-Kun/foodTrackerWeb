package ua.epam.food.dao.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
}

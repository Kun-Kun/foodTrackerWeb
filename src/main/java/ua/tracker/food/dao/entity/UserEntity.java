package ua.tracker.food.dao.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private boolean enabled;
}

package ua.epam.food.dao.entity;

import lombok.Data;

@Data
public class UserRoleEntity {
    private Integer id;
    private Integer userId;
    private Integer roleId;
}

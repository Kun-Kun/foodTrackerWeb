package ua.epam.food.dao.entity;

import lombok.Data;

@Data
public class RolePrivilegeEntity {
    private Integer id;
    private Integer roleId;
    private Integer privilegeId;
}

package ua.epam.food.core.security.context;

import lombok.Data;
import ua.epam.food.core.security.Privilege;
import ua.epam.food.core.security.Role;

import java.util.List;

@Data
public class SecurityData {
    private Integer getId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private List<Role> roles;
    private List<Privilege> privileges;

}

package ua.tracker.food.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ua.tracker.food.component.Gender;

import java.util.Date;

@Data
public class Profile {
    private Integer id;
	private Integer userId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private Integer goalId;
    private Integer dietId;
    private Integer physicalLoadLevelId;
    private Integer height;
    private Float weight;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date birthday;
}

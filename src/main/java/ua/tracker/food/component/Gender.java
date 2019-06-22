package ua.tracker.food.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Gender {

    DEFAULT("Choose..",1), MALE("Male",2), FEMALE("Female",3);

    private String text;
    private Integer id;

    private static Map<Integer,Gender> genderMap = new HashMap<>();

    static {
        for(Gender gender: Gender.values()) {
            genderMap.put(gender.id,gender);
        }
    }

    Gender(String text, Integer id) {
        this.text = text;
        this.id = id;
    }

    @JsonCreator
    public static Gender fromInteger(int id) {
        return genderMap.get(id);
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}

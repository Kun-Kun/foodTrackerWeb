package ua.tracker.food.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum RepastType {

    BREAKFAST("Breakfast",1), LUNCH("Lunch",2), DINNER("Dinner",3), SNACK("Snack",4);

    private String text;
    private Integer id;

    private static Map<Integer,RepastType> repastTypeMap = new HashMap<>();

    static {
        for(RepastType type: RepastType.values()) {
            repastTypeMap.put(type.id,type);
        }
    }

    RepastType(String text, Integer id) {
        this.text = text;
        this.id = id;
    }

    @JsonCreator
    public static RepastType fromInteger(int id) {
        return repastTypeMap.get(id);
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}

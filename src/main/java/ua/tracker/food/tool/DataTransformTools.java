package ua.tracker.food.tool;

import ua.tracker.food.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DataTransformTools {
    public static int calculateAge(LocalDate currentDate, LocalDate birthDate){
        return Period.between(birthDate,currentDate).getYears();
    }

    public static int calculateAge(Date birthDate){
        LocalDate currentDate = convertToLocalDate(new Date());
        LocalDate birthLocalDate = convertToLocalDate(birthDate);
        return Period.between(birthLocalDate,currentDate).getYears();
    }

    private static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static float multiplyFoodParameter(float value, float multiplier){
        return (value*multiplier)/100;
    }

    public static Float parseFloatValue(String value, String name) throws InvalidInputException {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            throw new InvalidInputException("Invalid value " + value + " for " + name);
        }
    }

    public static Integer parseInteger(String id) throws InvalidInputException {
        if (id != null && !id.isEmpty()) {
            try {
                return Integer.parseInt(id);
            } catch (NumberFormatException nfe) {
                throw new InvalidInputException("Invalid id");
            }
        } else {
            return null;
        }
    }

}

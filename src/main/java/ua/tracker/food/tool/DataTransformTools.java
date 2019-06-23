package ua.tracker.food.tool;

import ua.tracker.food.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataTransformTools {
		private static Logger log = LogManager.getLogger(DataTransformTools.class);
	
    public static int calculateAge(LocalDate currentDate, LocalDate birthDate){
        return Period.between(birthDate,currentDate).getYears();
    }

    public static int calculateAge(Date birthDate){
        LocalDate currentDate = convertToLocalDate(new Date());
        LocalDate birthLocalDate = convertToLocalDate(birthDate);
		log.log(Level.INFO, "Calculating age between {} and {}", currentDate,birthDate);
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
			log.log(Level.INFO, "Parsing {} with value {}",name,value);
            return Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
			log.error("Invalid format ",nfe);
            throw new InvalidInputException("Invalid value " + value + " for " + name);
        }
    }

    public static Integer parseInteger(String id) throws InvalidInputException {
        if (id != null && !id.isEmpty()) {
            try {
				log.log(Level.INFO, "Parsing integer id with value {}",id);
                return Integer.parseInt(id);
            } catch (NumberFormatException nfe) {
				log.error("Invalid format ",nfe);
                throw new InvalidInputException("Invalid id");
            }
        } else {
            return null;
        }
    }

}

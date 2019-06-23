package ua.tracker.food.tool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidationTools {
	private static Logger log = LogManager.getLogger(ValidationTools.class);
	
    public static boolean checkEmailFormat(String email){
		log.log(Level.INFO, "Checking  email format: {}", email);
        if (email==null){
			log.log(Level.INFO, "Email is null");
            return false;
        }
        return email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

}

package ua.tracker.food.services;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tracker.food.component.Gender;
import ua.tracker.food.controller.LoginServlet;
import ua.tracker.food.core.security.encoder.PasswordEncoder;
import ua.tracker.food.core.security.encoder.impl.ShaPasswordEncoder;
import ua.tracker.food.dao.entity.ProfileEntity;
import ua.tracker.food.dao.entity.UserEntity;
import ua.tracker.food.dao.repository.ProfileRepository;
import ua.tracker.food.dao.repository.UserRepository;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.tool.ValidationTools;

public class UserRegistrationServiceImpl implements UserRegistrationService {
	private Logger log = LogManager.getLogger(UserRegistrationServiceImpl.class);
    private static UserRegistrationServiceImpl instance;

    public static synchronized UserRegistrationServiceImpl getInstance(){
        if (instance==null){
            instance = new UserRegistrationServiceImpl();
        }
        return instance;
    }

    private UserRegistrationServiceImpl() {
    }

    private UserRepository userRepository = UserRepository.getInstance();
    private ProfileRepository profileRepository = ProfileRepository.getInstance();

    private boolean checkPasswordLength(String password){
		log.log(Level.INFO, "Checking password length ");
        if (password!=null) {
			log.log(Level.INFO, "Password length is {} ",password.length());
            return password.length() >= 8;
        }else {
            return false;
        }
    }

    private boolean checkPasswordsMatching(String password,String confirmPassword){
		log.log(Level.INFO, "Checking password matching ");
        if (password==null|| confirmPassword==null){
			log.log(Level.INFO, "One of password is null");
            return false;
        }
        return password.equals(confirmPassword);
    }

    private boolean usernameExists(String username) {
		log.log(Level.INFO, "Checking username {} existence", username);
        return userRepository.findByUsername(username) != null;
    }

    private boolean emailExists(String email) {
		log.log(Level.INFO, "Checking email {} existence", email);
        return profileRepository.findByEmail(email) != null;
    }

	@Override
    public boolean registerUser(String username,String email,String password,String confirmPassword) throws InvalidInputException{
		log.log(Level.INFO, "Checking fields");
        if (usernameExists(username)){
            throw new InvalidInputException("User with username is already exist");
        }
        if(ValidationTools.checkEmailFormat(email)==false){
            throw new InvalidInputException("Wrong email format");
        }
        if(emailExists(email)){
            throw new InvalidInputException("This email is already registered");
        }
        if(!checkPasswordLength(password)){
            throw new InvalidInputException("Password length must be at least 8 symbols long");
        }
        if (!checkPasswordsMatching(password,confirmPassword)){
            throw new InvalidInputException("Passwords don't match");
        }
		
		log.log(Level.INFO, "All fields have right format.");
        UserEntity userEntity = createUser(username,password);
        createProfile(userEntity.getId(),email);
        return true;
    }

    private UserEntity createUser(String username,String password){ 
		log.log(Level.INFO, "Creating new user");
        PasswordEncoder encoder = new ShaPasswordEncoder();
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(true);
        userEntity.setUsername(username);
        userEntity.setPassword(encoder.getHashedText(password));
        return userRepository.save(userEntity);
    }

    private ProfileEntity createProfile(Integer userId,String email){
		log.log(Level.INFO, "Creating default profile");
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(email);
        profileEntity.setUserId(userId);
        profileEntity.setDietId(1);
        profileEntity.setGoalId(1);
        profileEntity.setPhysicalLoadLevelId(1);
        profileEntity.setGender(Gender.DEFAULT);
        profileEntity.setHeight(170);
        profileEntity.setWeight(70F);
        return profileRepository.save(profileEntity);
    }

}

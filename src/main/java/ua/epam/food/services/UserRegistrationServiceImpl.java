package ua.epam.food.services;

import ua.epam.food.core.security.encoder.PasswordEncoder;
import ua.epam.food.core.security.encoder.impl.ShaPasswordEncoder;
import ua.epam.food.dao.entity.ProfileEntity;
import ua.epam.food.dao.entity.UserEntity;
import ua.epam.food.dao.repository.ProfileRepository;
import ua.epam.food.dao.repository.UserRepository;
import ua.epam.food.exception.InvalidInputException;
import ua.epam.food.tool.ValidationTools;

public class UserRegistrationServiceImpl implements UserRegistrationService {

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
        if (password!=null) {
            return password.length() >= 8;
        }else {
            return false;
        }
    }

    private boolean checkPasswordsMatching(String password,String confirmPassword){
        if (password==null|| confirmPassword==null){
            return false;
        }
        return password.equals(confirmPassword);
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private boolean emailExists(String email) {
        return profileRepository.findByEmail(email) != null;
    }


    public boolean registerUser(String username,String email,String password,String confirmPassword) throws InvalidInputException{
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
        UserEntity userEntity = createUser(username,password);
        createProfile(userEntity.getId(),email);
        return true;
    }

    private UserEntity createUser(String username,String password){
        PasswordEncoder encoder = new ShaPasswordEncoder();
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(true);
        userEntity.setUsername(username);
        userEntity.setPassword(encoder.getHashedText(password));
        return userRepository.save(userEntity);
    }

    private ProfileEntity createProfile(Integer userId,String email){
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(email);
        profileEntity.setUserId(userId);
        profileEntity.setDietId(1);
        profileEntity.setGoalId(1);
        profileEntity.setPhysicalLoadLevelId(1);
        profileEntity.setSexId(1);
        profileEntity.setHeight(170);
        profileEntity.setWeight(70F);
        return profileRepository.save(profileEntity);
    }

}

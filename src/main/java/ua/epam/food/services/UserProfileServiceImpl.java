package ua.epam.food.services;

import ua.epam.food.dao.entity.*;
import ua.epam.food.dao.repository.*;
import ua.epam.food.dto.ProfileSelectable;
import ua.epam.food.exception.InvalidInputException;
import ua.epam.food.mapper.ProfileMapper;
import ua.epam.food.mapper.ProfileMapperImpl;
import ua.epam.food.tool.ValidationTools;

import java.util.List;

public class UserProfileServiceImpl {

    private ProfileMapper profileMapper = new ProfileMapperImpl();
    private ProfileRepository profileRepository = ProfileRepository.getInstance();
    private DietRepository dietRepository = DietRepository.getInstance();
    private GoalRepository goalRepository = GoalRepository.getInstance();
    private PhysicalLoadLevelRepository physicalLoadLevelRepository = PhysicalLoadLevelRepository.getInstance();
    private SexRepository sexRepository = SexRepository.getInstance();

    private static UserProfileServiceImpl instance;

    public static synchronized UserProfileServiceImpl getInstance(){
        if (instance==null){
            instance = new UserProfileServiceImpl();
        }
        return instance;
    }

    private UserProfileServiceImpl() {
    }

    private List<DietEntity> getDietList(){
        return dietRepository.findAll();
    }

    private List<GoalEntity> getGoalList(){
        return goalRepository.findAll();
    }

    private List<PhysicalLoadLevelEntity> getGetPhysicalLoadLevelList(){
        return physicalLoadLevelRepository.findAll();
    }

    private List<SexEntity> getSexList(){
        return sexRepository.findAll();
    }

    public ProfileSelectable getProfileByProfileId(Integer profileId){
        ProfileEntity profile = profileRepository.findOne(profileId);
        List<DietEntity> dietEntities = getDietList();
        List<GoalEntity> goalEntities = getGoalList();
        List<PhysicalLoadLevelEntity> physicalLoadLevelEntities = getGetPhysicalLoadLevelList();
        List<SexEntity> getSexEntities = getSexList();
        return profileMapper.entityToDto(profile,dietEntities,goalEntities,physicalLoadLevelEntities,getSexEntities);
    }

    public void setDiet(Integer userId,Integer dietId) throws InvalidInputException{
        ProfileEntity profile = loadOrCreateProfile(userId);
        if(dietRepository.existsById(dietId)){
            profile.setDietId(dietId);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Selected diet not found");
        }
    }

    public void setGoal(Integer userId,Integer goalId) throws InvalidInputException{
        ProfileEntity profile = loadOrCreateProfile(userId);
        if(goalRepository.existsById(goalId)){
            profile.setGoalId(goalId);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Selected goal not found");
        }
    }

    public void setPhysicalLoadLevel(Integer userId,Integer physicalLoadLevel) throws InvalidInputException{
        ProfileEntity profile = loadOrCreateProfile(userId);
        if(physicalLoadLevelRepository.existsById(physicalLoadLevel)){
            profile.setPhysicalLoadLevelId(physicalLoadLevel);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Selected physical load level not found");
        }
    }

    public void setSex(Integer userId,Integer sexId) throws InvalidInputException{
        ProfileEntity profile = loadOrCreateProfile(userId);
        if(sexRepository.existsById(sexId)){
            profile.setSexId(sexId);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Selected sex not found");
        }
    }

    public void setFirstName(Integer userId,String firstName){
        if (firstName!=null&&firstName.length()<50){
            ProfileEntity profile = loadOrCreateProfile(userId);
            profile.setFirstName(firstName);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Your name is too long");
        }
    }

    public void setLastName(Integer userId,String lastName){
        if (lastName!=null&&lastName.length()<50){
            ProfileEntity profile = loadOrCreateProfile(userId);
            profile.setLastName(lastName);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Your name is too long");
        }
    }

    public void setHeight(Integer userId, Integer height){
        if (height!=null&&height<=250&&height>=50){
            ProfileEntity profile = loadOrCreateProfile(userId);
            profile.setHeight(height);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Your height is out of bounds 50-250");
        }
    }

    public void setWeight(Integer userId, Float weight){
        if (weight!=null&&weight<=250&&weight>=20){
            ProfileEntity profile = loadOrCreateProfile(userId);
            profile.setWeight(weight);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Your weight is out of bounds 20-250");
        }
    }

    public void setEmail(Integer userId,String email){
        if (!ValidationTools.checkEmailFormat(email)) {
            throw new InvalidInputException("Wrong email format");
        }
        if (email.length()<50){
            ProfileEntity profile = loadOrCreateProfile(userId);
            profile.setFirstName(email);
            profileRepository.save(profile);
        }else {
            throw new InvalidInputException("Your email is too long");
        }
    }

    private ProfileEntity createDefaultProfile(Integer userId){
        ProfileEntity entity = new ProfileEntity();
        entity.setDietId(1);
        entity.setSexId(1);
        entity.setPhysicalLoadLevelId(1);
        entity.setGoalId(1);
        entity.setUserId(userId);
        entity.setHeight(170);
        entity.setWeight(70F);
        return profileRepository.save(entity);
    }

    private ProfileEntity loadOrCreateProfile(Integer userId){
        ProfileEntity profile = profileRepository.findByUserId(userId);
        if (profile==null){
            profile = createDefaultProfile(userId);
        }
        return profile;
    }
    
    
}

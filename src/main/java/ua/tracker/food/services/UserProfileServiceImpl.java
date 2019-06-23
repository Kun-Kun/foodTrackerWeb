package ua.tracker.food.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.tracker.food.component.Gender;
import ua.tracker.food.controller.LoginServlet;
import ua.tracker.food.dto.ProfileSelectable;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.mapper.ProfileMapper;
import ua.tracker.food.mapper.ProfileMapperImpl;
import ua.tracker.food.tool.ValidationTools;

import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.dao.entity.GoalEntity;
import ua.tracker.food.dao.entity.PhysicalLoadLevelEntity;
import ua.tracker.food.dao.entity.ProfileEntity;
import ua.tracker.food.dao.repository.DietRepository;
import ua.tracker.food.dao.repository.GoalRepository;
import ua.tracker.food.dao.repository.PhysicalLoadLevelRepository;
import ua.tracker.food.dao.repository.ProfileRepository;

public class UserProfileServiceImpl implements UserProfileService{
	private Logger log = LogManager.getLogger(UserProfileServiceImpl.class);
	private ProfileMapper profileMapper = new ProfileMapperImpl();
	private ProfileRepository profileRepository = ProfileRepository.getInstance();
	private DietRepository dietRepository = DietRepository.getInstance();
	private GoalRepository goalRepository = GoalRepository.getInstance();
	private PhysicalLoadLevelRepository physicalLoadLevelRepository = PhysicalLoadLevelRepository.getInstance();

	private static UserProfileServiceImpl instance;

	public static synchronized UserProfileServiceImpl getInstance() {
		if (instance == null) {
			instance = new UserProfileServiceImpl();
		}
		return instance;
	}

	private UserProfileServiceImpl() {
	}

	private List<DietEntity> getDietList() {
		log.log(Level.INFO, "Loading diet list from repository");
		return dietRepository.findAll();
	}

	private List<GoalEntity> getGoalList() {
		log.log(Level.INFO, "Loading goal list from repository");
		return goalRepository.findAll();
	}

	private List<PhysicalLoadLevelEntity> getGetPhysicalLoadLevelList() {
		log.log(Level.INFO, "Loading pll list from repository");
		return physicalLoadLevelRepository.findAll();
	}

	private List<Gender> getGenderList() {
		log.log(Level.INFO, "Loading gender list from enum");
		return Arrays.asList(Gender.values());
	}

	@Override
	public ProfileSelectable getProfileSelectableByProfileId(Integer profileId) {
		log.log(Level.INFO, "Loading profile and selectable fields from repository by profile id {}",profileId);
		ProfileEntity profile = profileRepository.findOne(profileId);
		List<DietEntity> dietEntities = getDietList();
		List<GoalEntity> goalEntities = getGoalList();
		List<PhysicalLoadLevelEntity> physicalLoadLevelEntities = getGetPhysicalLoadLevelList();
		List<Gender> genderList = getGenderList();
		return profileMapper.entityToDto(profile, dietEntities, goalEntities, physicalLoadLevelEntities, genderList);
	}

	private void setDiet(Integer userId, Integer dietId) throws InvalidInputException {
		log.log(Level.INFO, "Setting diet {} in user {} profile",dietId,userId);
		ProfileEntity profile = loadOrCreateProfile(userId);
		if (dietRepository.existsById(dietId)) {
			profile.setDietId(dietId);
			profileRepository.save(profile);
			log.log(Level.INFO, "Diet is sucessfully saved");
		} else {
			throw new InvalidInputException("Selected diet not found");
		}
	}

	private void setGoal(Integer userId, Integer goalId) throws InvalidInputException {
		log.log(Level.INFO, "Setting goal {} in user {} profile",goalId,userId);
		ProfileEntity profile = loadOrCreateProfile(userId);
		if (goalRepository.existsById(goalId)) {
			profile.setGoalId(goalId);
			profileRepository.save(profile);
			log.log(Level.INFO, "Goal is sucessfully saved");
		} else {
			throw new InvalidInputException("Selected goal not found");
		}
	}

	private void setPhysicalLoadLevel(Integer userId, Integer physicalLoadLevel) throws InvalidInputException {
		log.log(Level.INFO, "Setting pll {} in user {} profile",physicalLoadLevel,userId);
		ProfileEntity profile = loadOrCreateProfile(userId);
		if (physicalLoadLevelRepository.existsById(physicalLoadLevel)) {
			profile.setPhysicalLoadLevelId(physicalLoadLevel);
			profileRepository.save(profile);
			log.log(Level.INFO, "Pll is sucessfully saved");
		} else {
			throw new InvalidInputException("Selected physical load level not found");
		}
	}

	private void setGender(Integer userId, Integer genderId) throws InvalidInputException {
		log.log(Level.INFO, "Setting gender {} in user {} profile",genderId,userId);
		ProfileEntity profile = loadOrCreateProfile(userId);
		Gender gender = Gender.fromInteger(genderId);
		if (gender==null){
			throw new InvalidInputException("Selected gender not found");
		}
		profile.setGender(gender);
		profileRepository.save(profile);
		log.log(Level.INFO, "Gender is sucessfully saved");
	}

	private void setFirstName(Integer userId, String firstName) {
		log.log(Level.INFO, "Setting first name {} in user {} profile",firstName,userId);
		if (firstName != null && firstName.length() < 50) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setFirstName(firstName);
			profileRepository.save(profile);
			log.log(Level.INFO, "First name is sucessfully saved");
		} else {
			throw new InvalidInputException("Your name is too long");
		}
	}

	private void setLastName(Integer userId, String lastName) {
		log.log(Level.INFO, "Setting last name {} in user {} profile",lastName,userId);
		if (lastName != null && lastName.length() < 50) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setLastName(lastName);
			profileRepository.save(profile);
			log.log(Level.INFO, "Last name is sucessfully saved");
		} else {
			throw new InvalidInputException("Your name is too long");
		}
	}

	private void setHeight(Integer userId, Integer height) {
		log.log(Level.INFO, "Setting height {} in user {} profile",height,userId);
		if (height != null && height <= 250 && height >= 50) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setHeight(height);
			profileRepository.save(profile);
			log.log(Level.INFO, "Height is sucessfully saved");
		} else {
			throw new InvalidInputException("Your height is out of bounds 50-250");
		}
	}

	private void setWeight(Integer userId, Float weight) {
		log.log(Level.INFO, "Setting weight {} in user {} profile",weight,userId);
		if (weight != null && weight <= 250 && weight >= 20) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setWeight(weight);
			profileRepository.save(profile);
			log.log(Level.INFO, "Weight is sucessfully saved");
		} else {
			throw new InvalidInputException("Your weight is out of bounds 20-250");
		}
	}

	private void setEmail(Integer userId, String email) {
		log.log(Level.INFO, "Setting email {} in user {} profile",email,userId);
		if (!ValidationTools.checkEmailFormat(email)) {
			throw new InvalidInputException("Wrong email format");
		}
		if (email.length() < 50) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setEmail(email);
			profileRepository.save(profile);
			log.log(Level.INFO, "Email is sucessfully saved");
		} else {
			throw new InvalidInputException("Your email is too long");
		}
	}

	private void setBirthday(Integer userId, String birthDay) {
		try{
			log.log(Level.INFO, "Setting birthday {} in user {} profile",birthDay,userId);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date date = simpleDateFormat.parse(birthDay);
			if(date.after(new Date())||date.before(simpleDateFormat.parse("01.01.1930"))){
				throw new InvalidInputException("Date out of years range 1930 - current time");
			}
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setBirthday(date);
			profileRepository.save(profile);
			log.log(Level.INFO, "Birthday is sucessfully saved");
		}catch(ParseException pe){
			throw new InvalidInputException("Unknown date format. Require dd.MM.yyyy");
		}
	}

	@Override
	public void setValue(Integer userId, String fieldName, String value) throws InvalidInputException {
		log.log(Level.INFO, "Setting {} = {} in user {} profile",fieldName,value,userId);
		switch (fieldName) {
			case "firstName":
				setFirstName(userId, value);
				break;
			case "lastName":
				setLastName(userId, value);
				break;
			case "email":
				setEmail(userId, value);
				break;
			case "birthday":
				setBirthday(userId, value);
				break;
			case "weight":
				setWeight(userId, Float.parseFloat(value));
				break;
			case "height":
				setHeight(userId, Integer.parseInt(value));
				break;
			case "physicalLoadLevel":
				setPhysicalLoadLevel(userId, Integer.parseInt(value));
				break;
			case "goal":
				setGoal(userId, Integer.parseInt(value));
				break;
			case "gender":
				setGender(userId, Integer.parseInt(value));
				break;
			case "diet":
				setDiet(userId, Integer.parseInt(value));
				break;
			default:
				throw new InvalidInputException("Unknown fild name " + fieldName);
		}
	}

	private ProfileEntity createDefaultProfile(Integer userId) {
		log.log(Level.INFO, "Creating defaul user {} profile",userId);
		ProfileEntity entity = new ProfileEntity();
		entity.setDietId(1);
		entity.setGender(Gender.DEFAULT);
		entity.setPhysicalLoadLevelId(1);
		entity.setGoalId(1);
		entity.setUserId(userId);
		entity.setHeight(170);
		entity.setWeight(70F);
		return profileRepository.save(entity);
	}

	private ProfileEntity loadOrCreateProfile(Integer userId) {
		ProfileEntity profile = profileRepository.findByUserId(userId);
		if (profile == null) {
			profile = createDefaultProfile(userId);
		}
		return profile;
	}

}

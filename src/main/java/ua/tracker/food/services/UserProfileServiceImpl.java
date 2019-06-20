package ua.tracker.food.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ua.tracker.food.dto.ProfileSelectable;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.mapper.ProfileMapper;
import ua.tracker.food.mapper.ProfileMapperImpl;
import ua.tracker.food.tool.ValidationTools;

import java.util.List;
import java.util.TimeZone;
import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.dao.entity.GoalEntity;
import ua.tracker.food.dao.entity.PhysicalLoadLevelEntity;
import ua.tracker.food.dao.entity.ProfileEntity;
import ua.tracker.food.dao.entity.SexEntity;
import ua.tracker.food.dao.repository.DietRepository;
import ua.tracker.food.dao.repository.GoalRepository;
import ua.tracker.food.dao.repository.PhysicalLoadLevelRepository;
import ua.tracker.food.dao.repository.ProfileRepository;
import ua.tracker.food.dao.repository.SexRepository;

public class UserProfileServiceImpl implements UserProfileService{

	private ProfileMapper profileMapper = new ProfileMapperImpl();
	private ProfileRepository profileRepository = ProfileRepository.getInstance();
	private DietRepository dietRepository = DietRepository.getInstance();
	private GoalRepository goalRepository = GoalRepository.getInstance();
	private PhysicalLoadLevelRepository physicalLoadLevelRepository = PhysicalLoadLevelRepository.getInstance();
	private SexRepository sexRepository = SexRepository.getInstance();

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
		return dietRepository.findAll();
	}

	private List<GoalEntity> getGoalList() {
		return goalRepository.findAll();
	}

	private List<PhysicalLoadLevelEntity> getGetPhysicalLoadLevelList() {
		return physicalLoadLevelRepository.findAll();
	}

	private List<SexEntity> getSexList() {
		return sexRepository.findAll();
	}

	public ProfileSelectable getProfileByProfileId(Integer profileId) {
		ProfileEntity profile = profileRepository.findOne(profileId);
		List<DietEntity> dietEntities = getDietList();
		List<GoalEntity> goalEntities = getGoalList();
		List<PhysicalLoadLevelEntity> physicalLoadLevelEntities = getGetPhysicalLoadLevelList();
		List<SexEntity> getSexEntities = getSexList();
		return profileMapper.entityToDto(profile, dietEntities, goalEntities, physicalLoadLevelEntities, getSexEntities);
	}

	private void setDiet(Integer userId, Integer dietId) throws InvalidInputException {
		ProfileEntity profile = loadOrCreateProfile(userId);
		if (dietRepository.existsById(dietId)) {
			profile.setDietId(dietId);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Selected diet not found");
		}
	}

	private void setGoal(Integer userId, Integer goalId) throws InvalidInputException {
		ProfileEntity profile = loadOrCreateProfile(userId);
		if (goalRepository.existsById(goalId)) {
			profile.setGoalId(goalId);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Selected goal not found");
		}
	}

	private void setPhysicalLoadLevel(Integer userId, Integer physicalLoadLevel) throws InvalidInputException {
		ProfileEntity profile = loadOrCreateProfile(userId);
		if (physicalLoadLevelRepository.existsById(physicalLoadLevel)) {
			profile.setPhysicalLoadLevelId(physicalLoadLevel);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Selected physical load level not found");
		}
	}

	private void setSex(Integer userId, Integer sexId) throws InvalidInputException {
		ProfileEntity profile = loadOrCreateProfile(userId);
		if (sexRepository.existsById(sexId)) {
			profile.setSexId(sexId);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Selected sex not found");
		}
	}

	private void setFirstName(Integer userId, String firstName) {
		if (firstName != null && firstName.length() < 50) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setFirstName(firstName);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Your name is too long");
		}
	}

	private void setLastName(Integer userId, String lastName) {
		if (lastName != null && lastName.length() < 50) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setLastName(lastName);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Your name is too long");
		}
	}

	private void setHeight(Integer userId, Integer height) {
		if (height != null && height <= 250 && height >= 50) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setHeight(height);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Your height is out of bounds 50-250");
		}
	}

	private void setWeight(Integer userId, Float weight) {
		if (weight != null && weight <= 250 && weight >= 20) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setWeight(weight);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Your weight is out of bounds 20-250");
		}
	}

	private void setEmail(Integer userId, String email) {
		if (!ValidationTools.checkEmailFormat(email)) {
			throw new InvalidInputException("Wrong email format");
		}
		if (email.length() < 50) {
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setEmail(email);
			profileRepository.save(profile);
		} else {
			throw new InvalidInputException("Your email is too long");
		}
	}

	private void setBirthday(Integer userId, String birthDay) {
		try{
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			df.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date date = df.parse(birthDay);
			if(date.after(new Date())||date.before(df.parse("01.01.1930"))){
				throw new InvalidInputException("Date out of years range 1930 - current time");
			}
			ProfileEntity profile = loadOrCreateProfile(userId);
			profile.setBirthday(date);
			profileRepository.save(profile);
		}catch(ParseException pe){
			throw new InvalidInputException("Unknown date format. Require dd.MM.yyyy");
		}
	}

	public void setValue(Integer userId, String fieldName, String value) throws InvalidInputException {
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
			case "sex":
				setSex(userId, Integer.parseInt(value));
				break;
			case "diet":
				setDiet(userId, Integer.parseInt(value));
				break;
			default:
				throw new InvalidInputException("Unknown fild name " + fieldName);
		}
	}

	private ProfileEntity createDefaultProfile(Integer userId) {
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

	private ProfileEntity loadOrCreateProfile(Integer userId) {
		ProfileEntity profile = profileRepository.findByUserId(userId);
		if (profile == null) {
			profile = createDefaultProfile(userId);
		}
		return profile;
	}

}

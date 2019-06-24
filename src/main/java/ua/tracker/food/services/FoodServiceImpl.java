package ua.tracker.food.services;

import ua.tracker.food.dao.entity.FoodEntity;
import ua.tracker.food.dao.repository.FoodRepository;
import ua.tracker.food.dto.Food;
import ua.tracker.food.exception.AccessDeniedException;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.mapper.FoodMapper;
import ua.tracker.food.mapper.FoodMapperImpl;
import ua.tracker.food.tool.DataTransformTools;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FoodServiceImpl implements FoodService{
    private static FoodServiceImpl instance;
    private FoodMapper foodMapper = new FoodMapperImpl();
    private FoodRepository foodRepository = FoodRepository.getInstance();
	private Logger log = LogManager.getLogger(FoodServiceImpl.class);
    private FoodServiceImpl() {
    }

    public static synchronized FoodServiceImpl getInstance() {
        if (instance == null) {
            instance = new FoodServiceImpl();
        }
        return instance;
    }



    private FoodEntity loadFoodEntityById(Integer id) throws InvalidInputException {
		log.log(Level.INFO, "Loading food entity by food id {}",id);
        FoodEntity foodEntity = foodRepository.findOne(id);
        if (foodEntity.getDeleted()){
			log.log(Level.INFO, "Food is deleted");
            throw new InvalidInputException("Food is deleted");
        }
        return foodEntity;
    }
	
	@Override
    public List<Food> searchFood(String text, Integer userId){
		log.log(Level.INFO, "User {} is searching food list by query {}",userId,text);
        List<FoodEntity> foods = foodRepository.findAllByTitleOrDescriptionContainsIgnoreCaseAndNotDeletedAndDefaultAndUserRecord(text,userId);
        return foods
                .stream()
                .map(entity ->foodMapper.entityToDto(entity))
                .collect(Collectors.toList());
    }

	@Override
    public List<Food> searchFood(String text){
		log.log(Level.INFO, "Searching food list by query {}",text);
        List<FoodEntity> foods = foodRepository.findAllByTitleOrDescriptionContainsIgnoreCaseAndNotDeletedAndDefaultRecord(text);
        return foods
                .stream()
                .map(entity ->foodMapper.entityToDto(entity))
                .collect(Collectors.toList());
    }

	@Override
    public Food deleteFood(String id, Integer userId) throws InvalidInputException, AccessDeniedException {
        Integer idInteger = DataTransformTools.parseInteger(id);
        if (idInteger == null || !foodRepository.existsById(idInteger)) {
            throw new InvalidInputException("Food not found");
        }
        FoodEntity foodEntity = loadFoodEntityById( idInteger);
        if (isUserCustomFood(foodEntity, userId)) {
            foodEntity.setDeleted(true);
            FoodEntity savedEntity = foodRepository.save(foodEntity);
            return foodMapper.entityToDto(savedEntity);
        } else {
            throw new AccessDeniedException("You do not have rights to edit record");
        }
    }

    private Food loadFoodById(Integer id) throws InvalidInputException {
        FoodEntity foodEntity = loadFoodEntityById(id);
        return foodMapper.entityToDto(foodEntity);
    }
    private Food loadDefaultFoodById(Integer id) throws InvalidInputException, AccessDeniedException {
        FoodEntity foodEntity = loadFoodEntityById(id);
        if(foodEntity.getDefaultRecord()==false){
            throw new AccessDeniedException("You do not have rights to load record");
        }
        return foodMapper.entityToDto(foodEntity);
    }

	@Override
    public Food loadFood(String id) throws InvalidInputException, AccessDeniedException{
		log.log(Level.INFO, "Loading food by id {}",id);
        Integer idInteger = DataTransformTools.parseInteger(id);
        if (idInteger == null || !foodRepository.existsById(idInteger)) {
			log.log(Level.INFO, "Food not found");
            throw new InvalidInputException("Food not found");
        }
        return loadDefaultFoodById(idInteger);
    }

	@Override
    public Food loadFood(String id, Integer userId) throws InvalidInputException, AccessDeniedException {
		log.log(Level.INFO, "Loading food id {} by user {}",id,userId);
        Integer idInteger = DataTransformTools.parseInteger(id);
        if (idInteger == null || !foodRepository.existsById(idInteger)) {
			log.log(Level.INFO, "Food not found");
            throw new InvalidInputException("Food not found");
        }

        if (isUserCustomFood(idInteger, userId)) {
            return loadFoodById(idInteger);
        } else {
			log.log(Level.INFO, "Food does not belong to the user");
            throw new AccessDeniedException("You do not have rights to edit record");
        }
    }

    private boolean isUserCustomFood(Integer foodId, Integer userId) {
        FoodEntity foodEntity = foodRepository.findOne(foodId);
        return isUserCustomFood(foodEntity,userId);
    }

    private boolean isUserCustomFood(FoodEntity foodEntity, Integer userId) {
        if (foodEntity != null && foodEntity.getCreatorId() != null) {
            return foodEntity.getCreatorId().equals(userId);
        } else {
            return false;
        }
    }

	@Override
    public Food saveFood(String id, String title, String description, String fats, String proteins, String carbohydrates, String kilocalories, String weight, Integer userId) throws InvalidInputException, AccessDeniedException {
        log.log(Level.INFO, "Saving food by user {}",userId);
		Integer idInteger = DataTransformTools.parseInteger(id);
        Float fatsFloat = DataTransformTools.parseFloatValue(fats, "fats");
        Float proteinsFloat = DataTransformTools.parseFloatValue(proteins, "proteins");
        Float carbohydratesFloat = DataTransformTools.parseFloatValue(carbohydrates, "carbohydrates");
        Float kilocaloriesFloat = DataTransformTools.parseFloatValue(kilocalories, "kilocalories");
        Float weightFloat = DataTransformTools.parseFloatValue(weight, "weight");
        checkFields(title, description, fatsFloat, proteinsFloat, carbohydratesFloat, kilocaloriesFloat, weightFloat);
        FoodEntity foodEntity = null;
        if (idInteger == null || !foodRepository.existsById(idInteger)) {
			log.log(Level.INFO, "No food id provided. Creating new food record");
            foodEntity = new FoodEntity();
            foodEntity.setCreatorId(userId);
            foodEntity.setCreationDate(new Date());
            foodEntity.setDefaultRecord(false);
            foodEntity.setGalleryId(1);
            foodEntity.setDeleted(false);
        } else {
            foodEntity = foodRepository.findOne(idInteger);
			log.log(Level.INFO, "Finding food by id {}", idInteger);
            checkPermission(foodEntity, userId);
        }
        foodEntity.setTitle(title);
        foodEntity.setDescription(description);
        foodEntity.setFats(fatsFloat);
        foodEntity.setProteins(proteinsFloat);
        foodEntity.setCarbohydrates(carbohydratesFloat);
        foodEntity.setKilocalories(kilocaloriesFloat);
        foodEntity.setPortionWeight(weightFloat);
        FoodEntity savedEntity = foodRepository.save(foodEntity);
		log.log(Level.INFO, "Food succeessfully saved");
        return foodMapper.entityToDto(savedEntity);
    }



    private void checkPermission(FoodEntity entity, Integer userId) throws AccessDeniedException {
		log.log(Level.INFO, "Checking permission for user {} to edit {}", userId,entity);
        if (!userId.equals(entity.getCreatorId()) || entity.getDeleted()) {
			log.log(Level.INFO, "User {} not alowed to edit {}", userId,entity);
            throw new AccessDeniedException("You do not have rights to edit record");
        }
    }

    private void checkFields(String title, String description, float fats, float proteins, float carbohydrates, float kilocalories, float weight) throws InvalidInputException {
		log.log(Level.INFO, "Checking input fields");
        if (!checkTitle(title)) {
            throw new InvalidInputException("Title size must be between 2 and 255 characters.");
        }
        if (!checkDescription(description)) {
            throw new InvalidInputException("Description size must be between 100 and 10000 characters.");
        }
        if (!checkPart(fats)) {
            throw new InvalidInputException("Part of fats per 100 grams of product can not be more than 100 grams");
        }
        if (!checkPart(proteins)) {
            throw new InvalidInputException("Part of proteins per 100 grams of product can not be more than 100 grams");
        }
        if (!checkPart(carbohydrates)) {
            throw new InvalidInputException("Part of carbohydrates per 100 grams of product can not be more than 100 grams");
        }
        if (!checkParts(fats, proteins, carbohydrates)) {
            throw new InvalidInputException("Total value of fats,proteins,carbohydrates per 100 grams of product can not be more than 100 grams");
        }
        if (!checkKilocalories(kilocalories)) {
            throw new InvalidInputException("Kilocalories per 100 grams of product can not be more than 900 kcal");
        }
        if (!checkWeight(weight)) {
            throw new InvalidInputException("Weight must be > 0");
        }
    }

    private boolean checkTitle(String title) {
		log.log(Level.INFO, "Checking title");
        if (title == null) {
            return false;
        }
        return title.length() > 2 && title.length() < 255;
    }

    private boolean checkDescription(String description) {
		log.log(Level.INFO, "Checking description");
        if (description == null) {
            return false;
        }
        return description.length() > 100 && description.length() < 10000;
    }

    private boolean checkPart(float part) {
		log.log(Level.INFO, "Checking nutrient");
        return part >= 0 && part < 100;
    }

    private boolean checkParts(float fats, float proteins, float carbohydrates) {
        float total = fats + proteins + carbohydrates;
        return total < 100;
    }

    private boolean checkKilocalories(float kilocalories) {
		log.log(Level.INFO, "Checking kilocalories");
        return kilocalories >= 0 && kilocalories <= 900;
    }

    private boolean checkWeight(float weight) {
		log.log(Level.INFO, "Checking portion weight");
        return weight >= 0;
    }
}

package ua.epam.food.services;

import ua.epam.food.dao.entity.FoodEntity;
import ua.epam.food.dao.repository.FoodRepository;
import ua.epam.food.dto.Food;
import ua.epam.food.exception.AccessDeniedException;
import ua.epam.food.exception.InvalidInputException;
import ua.epam.food.mapper.FoodMapper;
import ua.epam.food.mapper.FoodMapperImpl;
import ua.epam.food.tool.ControllerTools;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FoodServiceImpl {
    private static FoodServiceImpl instance;
    private FoodMapper foodMapper = new FoodMapperImpl();
    private FoodRepository foodRepository = FoodRepository.getInstance();

    private FoodServiceImpl() {
    }

    public static synchronized FoodServiceImpl getInstance() {
        if (instance == null) {
            instance = new FoodServiceImpl();
        }
        return instance;
    }



    private FoodEntity loadFoodEntityById(Integer id) throws InvalidInputException {
        FoodEntity foodEntity = foodRepository.findOne(id);
        if (foodEntity.getDeleted()){
            throw new InvalidInputException("Food is deleted");
        }
        return foodEntity;
    }

    public List<Food> searchFood(String text, Integer userId){
        List<FoodEntity> foods = foodRepository.findAllByTitleOrDescriptionContainsIgnoreCaseAndNotDeletedAndDefaultAndUserRecord(text,userId);
        return foods
                .stream()
                .map(entity ->foodMapper.entityToDto(entity))
                .collect(Collectors.toList());
    }

    public List<Food> searchFood(String text){
        List<FoodEntity> foods = foodRepository.findAllByTitleOrDescriptionContainsIgnoreCaseAndNotDeletedAndDefaultRecord(text);
        return foods
                .stream()
                .map(entity ->foodMapper.entityToDto(entity))
                .collect(Collectors.toList());
    }

    public Food deleteFood(String id, Integer userId) throws InvalidInputException, AccessDeniedException {
        Integer idInteger = parseId(id);
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

    public Food loadFoodById(Integer id) throws InvalidInputException {
        FoodEntity foodEntity = loadFoodEntityById(id);
        return foodMapper.entityToDto(foodEntity);
    }

    public Food loadFood(String id) throws InvalidInputException {
        Integer idInteger = parseId(id);
        if (idInteger == null || !foodRepository.existsById(idInteger)) {
            throw new InvalidInputException("Food not found");
        }
        return loadFoodById(idInteger);
    }

    public Food loadFood(String id, Integer userId) throws InvalidInputException, AccessDeniedException {
        Integer idInteger = parseId(id);
        if (idInteger == null || !foodRepository.existsById(idInteger)) {
            throw new InvalidInputException("Food not found");
        }

        if (isUserCustomFood(idInteger, userId)) {
            return loadFoodById(idInteger);
        } else {
            throw new AccessDeniedException("You do not have rights to edit record");
        }
    }

    public boolean isUserCustomFood(Integer foodId, Integer userId) {
        FoodEntity foodEntity = foodRepository.findOne(foodId);
        return isUserCustomFood(foodEntity,userId);
    }

    public boolean isUserCustomFood(FoodEntity foodEntity, Integer userId) {
        if (foodEntity != null && foodEntity.getCreatorId() != null) {
            return foodEntity.getCreatorId().equals(userId);
        } else {
            return false;
        }
    }

    public Food saveFood(String id, String title, String description, String fats, String proteins, String carbohydrates, String kilocalories, String weight, Integer userId) throws InvalidInputException, AccessDeniedException {
        Integer idInteger = parseId(id);
        Float fatsFloat = parseFloatValue(fats, "fats");
        Float proteinsFloat = parseFloatValue(proteins, "proteins");
        Float carbohydratesFloat = parseFloatValue(carbohydrates, "carbohydrates");
        Float kilocaloriesFloat = parseFloatValue(kilocalories, "kilocalories");
        Float weightFloat = parseFloatValue(weight, "weight");
        checkFields(title, description, fatsFloat, proteinsFloat, carbohydratesFloat, kilocaloriesFloat, weightFloat);
        FoodEntity foodEntity = null;
        if (idInteger == null || !foodRepository.existsById(idInteger)) {
            foodEntity = new FoodEntity();
            foodEntity.setCreatorId(userId);
            foodEntity.setCreationDate(new Date());
            foodEntity.setDefaultRecord(false);
            foodEntity.setGalleryId(1);
            foodEntity.setDeleted(false);
        } else {
            foodEntity = foodRepository.findOne(idInteger);
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
        return foodMapper.entityToDto(savedEntity);
    }

    public Float parseFloatValue(String value, String name) throws InvalidInputException {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            throw new InvalidInputException("Invalid value " + value + " for " + name);
        }
    }

    public Integer parseId(String id) throws InvalidInputException {
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

    public void checkPermission(FoodEntity entity, Integer userId) throws AccessDeniedException {
        if (!userId.equals(entity.getCreatorId()) || entity.getDeleted()) {
            throw new AccessDeniedException("You do not have rights to edit record");
        }
    }

    public void checkFields(String title, String description, float fats, float proteins, float carbohydrates, float kilocalories, float weight) throws InvalidInputException {
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

    public boolean checkTitle(String title) {
        if (title == null) {
            return false;
        }
        return title.length() > 2 && title.length() < 255;
    }

    public boolean checkDescription(String description) {
        if (description == null) {
            return false;
        }
        return description.length() > 100 && description.length() < 10000;
    }

    public boolean checkPart(float part) {
        return part >= 0 && part < 100;
    }

    public boolean checkParts(float fats, float proteins, float carbohydrates) {
        float total = fats + proteins + carbohydrates;
        return total < 100;
    }

    public boolean checkKilocalories(float kilocalories) {
        return kilocalories >= 0 && kilocalories <= 900;
    }

    public boolean checkWeight(float weight) {
        return weight >= 0;
    }
}

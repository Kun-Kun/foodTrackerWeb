package ua.tracker.food.services;

import ua.tracker.food.component.RepastType;
import ua.tracker.food.dao.entity.DailyGoalEntity;
import ua.tracker.food.dao.entity.EatenRecordEntity;
import ua.tracker.food.dao.entity.FoodEntity;
import ua.tracker.food.dao.repository.DailyGoalRepository;
import ua.tracker.food.dao.repository.EatenRecordRepository;
import ua.tracker.food.dao.repository.FoodRepository;
import ua.tracker.food.dto.EatenTrackerRecord;
import ua.tracker.food.exception.InvalidInputException;
import ua.tracker.food.exception.InvalidResultException;
import ua.tracker.food.mapper.EatenRecordTransformer;
import ua.tracker.food.mapper.EatenRecordTransformerImpl;
import ua.tracker.food.mapper.EatenTrackerRecordMapper;
import ua.tracker.food.mapper.EatenTrackerRecordMapperImpl;
import ua.tracker.food.tool.DataTransformTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class TrackerServiceImpl {

    private static TrackerServiceImpl instance;

    public static synchronized TrackerServiceImpl getInstance() {
        if (instance == null) {
            instance = new TrackerServiceImpl();
        }
        return instance;
    }

    private TrackerServiceImpl() {
    }


    EatenTrackerRecordMapper eatenTrackerRecordMapper = new EatenTrackerRecordMapperImpl();
    CaloriesCalculationService caloriesCalculationService = CaloriesCalculationService.getInstance();
    EatenRecordTransformer eatenRecordTransformer = new EatenRecordTransformerImpl();

    private DailyGoalRepository dailyGoalRepository = DailyGoalRepository.getInstance();

    private EatenRecordRepository eatenRecordRepository = EatenRecordRepository.getInstance();
    private FoodRepository foodRepository = FoodRepository.getInstance();


    public void addEatenFood(String dateString, String foodId, String amountString, Integer userId, String repastTypeId) throws InvalidInputException {
        FoodEntity foodEntity = null;

        try {
            Integer foodIdInteger = DataTransformTools.parseInteger(foodId);
            foodEntity = foodRepository.findOne(foodIdInteger);
            if (foodEntity.getDeleted()) {
                throw new InvalidInputException("Food not found");
            }
            if (!foodEntity.getDefaultRecord() && !userId.equals(foodEntity.getCreatorId())){
                throw new InvalidInputException("Food does not belong to the user");
            }
        } catch (InvalidResultException ire) {
            throw new InvalidInputException("Food not found");
        }
        float amount = DataTransformTools.parseFloatValue(amountString,"amount");
        if (amount<=0){
            throw new InvalidInputException("Wrong amount of food");
        }

        Date date = parseDateString(dateString);

        RepastType repastType =  parseRepastTypeFromInt(repastTypeId);

        DailyGoalEntity dailyGoalEntity = dailyGoalRepository.findOneByEventDateAndUserId(date,userId);
        if(dailyGoalEntity==null) {
            dailyGoalEntity = caloriesCalculationService.createNewDailyGoalEntity(date, userId);
            dailyGoalEntity = dailyGoalRepository.save(dailyGoalEntity);
        }


        EatenRecordEntity eatenRecordEntity = eatenRecordTransformer.transform(foodEntity,dailyGoalEntity.getId(),repastType,amount);
        eatenRecordRepository.save(eatenRecordEntity);
    }

    public List<EatenTrackerRecord> loadEatenFoodList(String dateString,Integer userId, String repastTypeId){
        Date date = parseDateString(dateString);
        RepastType repastType = parseRepastTypeFromInt(repastTypeId);
        List<EatenRecordEntity> eatenRecordEntities = eatenRecordRepository.findAllByEventDateAndUserIdAndRepastType(date,userId,repastType);
        return eatenRecordEntities
                .stream()
                .map(eatenRecordEntity -> {
                    FoodEntity foodEntity = foodRepository.findOne(eatenRecordEntity.getFoodId());
                    return eatenTrackerRecordMapper.mapFromEntities(eatenRecordEntity, foodEntity);
                }).collect(Collectors.toList());
    }


    private Date parseDateString(String dateString) throws InvalidInputException{
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.parse(dateString);
        }catch(ParseException pe){
            throw new InvalidInputException("Unknown date format. Require dd.MM.yyyy");
        }
    }

    private RepastType parseRepastTypeFromInt(String repastTypeId) throws InvalidInputException{
        Integer repastTypeInteger = DataTransformTools.parseInteger(repastTypeId);
        RepastType repastType = RepastType.fromInteger(repastTypeInteger);
        if (repastType==null){
            throw new InvalidInputException("Unknown rapast type");
        }
        return repastType;
    }

}

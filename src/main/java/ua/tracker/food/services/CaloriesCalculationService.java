package ua.tracker.food.services;

import ua.tracker.food.component.UserParameters;
import ua.tracker.food.component.norma.calories.CaloriesNormCalculator;
import ua.tracker.food.component.norma.calories.CaloriesNormCalculatorImpl;
import ua.tracker.food.component.norma.calories.HarrisBenedictBasalMetabolicRateCalculator;
import ua.tracker.food.dao.entity.*;
import ua.tracker.food.dao.repository.DietRepository;
import ua.tracker.food.dao.repository.GoalRepository;
import ua.tracker.food.dao.repository.PhysicalLoadLevelRepository;
import ua.tracker.food.dao.repository.ProfileRepository;
import ua.tracker.food.dto.Profile;
import ua.tracker.food.mapper.ProfileMapper;
import ua.tracker.food.mapper.ProfileMapperImpl;

import java.util.Date;

public class CaloriesCalculationService {

    private static CaloriesCalculationService instance;

    public static synchronized CaloriesCalculationService getInstance() {
        if (instance == null) {
            instance = new CaloriesCalculationService();
        }
        return instance;
    }

    private CaloriesCalculationService() {
    }


    private ProfileMapper profileMapper = new ProfileMapperImpl();

    private CaloriesNormCalculator caloriesNormCalculator = new CaloriesNormCalculatorImpl(new HarrisBenedictBasalMetabolicRateCalculator());
    private ProfileRepository profileRepository = ProfileRepository.getInstance();
    private DietRepository dietRepository = DietRepository.getInstance();
    private GoalRepository goalRepository = GoalRepository.getInstance();
    private PhysicalLoadLevelRepository physicalLoadLevelRepository = PhysicalLoadLevelRepository.getInstance();


    public DailyGoalEntity createNewDailyGoalEntity(Date date, Integer userId){
        ProfileEntity profileEntity = profileRepository.findByUserId(userId);
        float dailyKilocalories = calculateDailyKilocalories(profileEntity);
        DailyGoalEntity dailyGoalEntity = new DailyGoalEntity();
        dailyGoalEntity.setUserId(userId);
        dailyGoalEntity.setEventDate(date);
        dailyGoalEntity.setDietId(profileEntity.getDietId());
        dailyGoalEntity.setKilocalories(dailyKilocalories);
        return dailyGoalEntity;
    }

    private float calculateDailyKilocalories(ProfileEntity profileEntity){
        GoalEntity goalEntity = goalRepository.findOne(profileEntity.getGoalId());
        PhysicalLoadLevelEntity physicalLoadLevelEntity = physicalLoadLevelRepository.findOne(profileEntity.getPhysicalLoadLevelId());
        UserParameters userParameters = profileMapper.userParametersFromProfile(profileEntity);
        return caloriesNormCalculator.calculateNorm(userParameters,goalEntity.getCoefficient(),physicalLoadLevelEntity.getCoefficient());
    }

}

package ua.tracker.food.mapper;

import ua.tracker.food.component.RepastType;
import ua.tracker.food.dao.entity.EatenRecordEntity;
import ua.tracker.food.dao.entity.FoodEntity;
import ua.tracker.food.tool.DataTransformTools;

import java.util.Date;


public class EatenRecordTransformerImpl implements EatenRecordTransformer {

    @Override
    public EatenRecordEntity transform(FoodEntity foodEntity, Integer dailyGoalId, RepastType repastType, Float amount) {
        if (foodEntity == null && dailyGoalId == null && repastType == null && amount == null) {
            return null;
        }

        EatenRecordEntity eatenRecordEntity = new EatenRecordEntity();

        if (foodEntity != null) {
            eatenRecordEntity.setFoodId(foodEntity.getId());
            eatenRecordEntity.setPortionWeight(amount);
            eatenRecordEntity.setFats(DataTransformTools.multiplyFoodParameter(foodEntity.getFats(), amount));
            eatenRecordEntity.setProteins(DataTransformTools.multiplyFoodParameter(foodEntity.getProteins(), amount));
            eatenRecordEntity.setCarbohydrates(DataTransformTools.multiplyFoodParameter(foodEntity.getCarbohydrates(), amount));
            eatenRecordEntity.setKilocalories(DataTransformTools.multiplyFoodParameter(foodEntity.getKilocalories(), amount));
            eatenRecordEntity.setChangeDate(new Date());
        }
        if (dailyGoalId != null) {
            eatenRecordEntity.setDailyGoalId(dailyGoalId);
        }
        if (repastType != null) {
            eatenRecordEntity.setRepastType(repastType);
        }

        return eatenRecordEntity;
    }
}

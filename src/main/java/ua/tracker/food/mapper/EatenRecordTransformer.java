package ua.tracker.food.mapper;


import ua.tracker.food.component.RepastType;
import ua.tracker.food.dao.entity.EatenRecordEntity;
import ua.tracker.food.dao.entity.FoodEntity;

public interface EatenRecordTransformer {

    EatenRecordEntity transform(FoodEntity foodEntity, Integer dailyGoalId, RepastType repastType, Float value);
}

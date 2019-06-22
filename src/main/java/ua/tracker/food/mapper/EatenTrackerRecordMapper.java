package ua.tracker.food.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.tracker.food.dao.entity.EatenRecordEntity;
import ua.tracker.food.dao.entity.FoodEntity;
import ua.tracker.food.dto.EatenTrackerRecord;


public interface EatenTrackerRecordMapper {
    EatenTrackerRecord mapFromEntities(EatenRecordEntity eatenRecordEntity, FoodEntity foodEntity);
}

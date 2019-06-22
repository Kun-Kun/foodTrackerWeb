package ua.tracker.food.mapper;

import ua.tracker.food.dao.entity.EatenRecordEntity;
import ua.tracker.food.dao.entity.FoodEntity;
import ua.tracker.food.dto.EatenTrackerRecord;

import javax.annotation.Generated;

public class EatenTrackerRecordMapperImpl implements EatenTrackerRecordMapper {

    @Override
    public EatenTrackerRecord mapFromEntities(EatenRecordEntity eatenRecordEntity, FoodEntity foodEntity) {
        if ( eatenRecordEntity == null && foodEntity == null ) {
            return null;
        }

        EatenTrackerRecord eatenTrackerRecord = new EatenTrackerRecord();

        if ( eatenRecordEntity != null ) {
            eatenTrackerRecord.setCarbohydrates( eatenRecordEntity.getCarbohydrates() );
            eatenTrackerRecord.setKilocalories( eatenRecordEntity.getKilocalories() );
            eatenTrackerRecord.setFats( eatenRecordEntity.getFats() );
            eatenTrackerRecord.setProteins( eatenRecordEntity.getProteins() );
            eatenTrackerRecord.setId( eatenRecordEntity.getId() );
            eatenTrackerRecord.setPortionWeight( eatenRecordEntity.getPortionWeight() );
            eatenTrackerRecord.setRepastType( eatenRecordEntity.getRepastType() );
        }
        if ( foodEntity != null ) {
            eatenTrackerRecord.setFoodId( foodEntity.getId() );
            eatenTrackerRecord.setTitle( foodEntity.getTitle() );
            eatenTrackerRecord.setDescription( foodEntity.getDescription() );
        }

        return eatenTrackerRecord;
    }
}

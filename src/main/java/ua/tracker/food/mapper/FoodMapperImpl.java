package ua.tracker.food.mapper;

import ua.tracker.food.dao.entity.FoodEntity;
import ua.tracker.food.dto.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodMapperImpl implements FoodMapper {

    @Override
    public Food entityToDto(FoodEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Food food = new Food();

        food.setId( entity.getId() );
        food.setTitle( entity.getTitle() );
        food.setDescription( entity.getDescription() );
        food.setPortionWeight( entity.getPortionWeight() );
        food.setFats( entity.getFats() );
        food.setProteins( entity.getProteins() );
        food.setCarbohydrates( entity.getCarbohydrates() );
        food.setKilocalories( entity.getKilocalories() );
        food.setCreatorId( entity.getCreatorId() );
        food.setDefaultRecord( entity.getDefaultRecord() );
        food.setCreationDate( entity.getCreationDate() );
        food.setDeleted(entity.getDeleted());

        return food;
    }

    @Override
    public Food entityToDto(FoodEntity entity, List<String> imageUrls) {
        if ( entity == null && imageUrls == null ) {
            return null;
        }

        Food food = new Food();

        if ( entity != null ) {
            food.setId( entity.getId() );
            food.setTitle( entity.getTitle() );
            food.setDescription( entity.getDescription() );
            food.setPortionWeight( entity.getPortionWeight() );
            food.setFats( entity.getFats() );
            food.setProteins( entity.getProteins() );
            food.setCarbohydrates( entity.getCarbohydrates() );
            food.setKilocalories( entity.getKilocalories() );
            food.setCreatorId( entity.getCreatorId() );
            food.setDefaultRecord( entity.getDefaultRecord() );
            food.setCreationDate( entity.getCreationDate() );
            food.setDeleted(entity.getDeleted());
        }
        if ( imageUrls != null ) {
            List<String> list = imageUrls;
            if ( list != null ) {
                food.setImageUrls( new ArrayList<String>( list ) );
            }
        }

        return food;
    }
}

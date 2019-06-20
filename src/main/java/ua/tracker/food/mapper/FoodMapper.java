package ua.tracker.food.mapper;

import ua.tracker.food.dao.entity.FoodEntity;
import ua.tracker.food.dto.Food;

import java.util.List;

public interface FoodMapper {
    Food entityToDto(FoodEntity entity);
    Food entityToDto(FoodEntity entity,List<String> imageUrls);
}

package ua.epam.food.mapper;

import ua.epam.food.dao.entity.FoodEntity;
import ua.epam.food.dto.Food;

import java.util.List;

public interface FoodMapper {
    Food entityToDto(FoodEntity entity);
    Food entityToDto(FoodEntity entity,List<String> imageUrls);
}

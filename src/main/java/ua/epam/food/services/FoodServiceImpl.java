package ua.epam.food.services;

import ua.epam.food.dao.entity.*;
import ua.epam.food.dao.repository.*;
import ua.epam.food.dto.Food;
import ua.epam.food.dto.ProfileSelectable;
import ua.epam.food.exception.InvalidInputException;
import ua.epam.food.mapper.FoodMapper;
import ua.epam.food.mapper.FoodMapperImpl;
import ua.epam.food.mapper.ProfileMapper;
import ua.epam.food.mapper.ProfileMapperImpl;
import ua.epam.food.tool.ValidationTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FoodServiceImpl {
	private FoodMapper foodMapper = new FoodMapperImpl();
	private FoodRepository foodRepository = FoodRepository.getInstance();
	private ProfileRepository profileRepository = ProfileRepository.getInstance();


	private static FoodServiceImpl instance;

	public static synchronized FoodServiceImpl getInstance() {
		if (instance == null) {
			instance = new FoodServiceImpl();
		}
		return instance;
	}

	private FoodServiceImpl() {
	}

	private FoodEntity loadFoodEntityById(Integer id){
		return foodRepository.findOne(id);
	}

	public Food loadFoodById(Integer id){
		FoodEntity foodEntity = foodRepository.findOne(id);
		return foodMapper.entityToDto(foodEntity);
	}

}

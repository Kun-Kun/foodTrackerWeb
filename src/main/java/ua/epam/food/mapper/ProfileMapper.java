package ua.epam.food.mapper;

import ua.epam.food.dao.entity.ProfileEntity;
import ua.epam.food.dto.Profile;

public interface ProfileMapper {
    Profile entityToDto(ProfileEntity entity);

}

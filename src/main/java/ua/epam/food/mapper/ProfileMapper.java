package ua.epam.food.mapper;

import ua.epam.food.dao.entity.*;
import ua.epam.food.dto.Profile;
import ua.epam.food.dto.ProfileSelectable;

import java.util.List;

public interface ProfileMapper {
    Profile entityToDto(ProfileEntity entity);
    ProfileSelectable entityToDto(ProfileEntity entity, List<DietEntity> dietEntities, List<GoalEntity> goalEntities, List<PhysicalLoadLevelEntity> physicalLoadLevelEntities, List<SexEntity> sexEntities);

}

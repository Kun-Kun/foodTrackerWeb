package ua.tracker.food.mapper;

import ua.tracker.food.dto.Profile;
import ua.tracker.food.dto.ProfileSelectable;

import java.util.List;
import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.dao.entity.GoalEntity;
import ua.tracker.food.dao.entity.PhysicalLoadLevelEntity;
import ua.tracker.food.dao.entity.ProfileEntity;
import ua.tracker.food.dao.entity.SexEntity;

public interface ProfileMapper {
    Profile entityToDto(ProfileEntity entity);
    ProfileSelectable entityToDto(ProfileEntity entity, List<DietEntity> dietEntities, List<GoalEntity> goalEntities, List<PhysicalLoadLevelEntity> physicalLoadLevelEntities, List<SexEntity> sexEntities);

}

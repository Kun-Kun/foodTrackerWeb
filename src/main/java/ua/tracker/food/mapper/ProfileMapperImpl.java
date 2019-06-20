package ua.tracker.food.mapper;

import ua.tracker.food.dto.Profile;
import ua.tracker.food.dto.ProfileSelectable;

import java.util.List;
import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.dao.entity.GoalEntity;
import ua.tracker.food.dao.entity.PhysicalLoadLevelEntity;
import ua.tracker.food.dao.entity.ProfileEntity;
import ua.tracker.food.dao.entity.SexEntity;

public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public Profile entityToDto(ProfileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setId( entity.getId() );
		profile.setUserId(entity.getUserId());
        profile.setFirstName( entity.getFirstName() );
        profile.setLastName( entity.getLastName() );
        profile.setEmail( entity.getEmail() );
        profile.setGoalId( entity.getGoalId() );
        profile.setDietId( entity.getDietId() );
        profile.setPhysicalLoadLevelId( entity.getPhysicalLoadLevelId() );
        profile.setSexId( entity.getSexId() );
        profile.setHeight( entity.getHeight() );
        profile.setWeight( entity.getWeight() );
        profile.setBirthday(entity.getBirthday());
        return profile;
    }

    @Override
    public ProfileSelectable entityToDto(ProfileEntity entity, List<DietEntity> dietEntities, List<GoalEntity> goalEntities, List<PhysicalLoadLevelEntity> physicalLoadLevelEntities, List<SexEntity> sexEntities) {
        if ( entity == null ) {
            return null;
        }

        ProfileSelectable profile = new ProfileSelectable();

        profile.setId( entity.getId() );
		profile.setUserId(entity.getUserId());
        profile.setFirstName( entity.getFirstName() );
        profile.setLastName( entity.getLastName() );
        profile.setEmail( entity.getEmail() );
        profile.setGoalId( entity.getGoalId() );
        profile.setDietId( entity.getDietId() );
        profile.setPhysicalLoadLevelId( entity.getPhysicalLoadLevelId() );
        profile.setSexId( entity.getSexId() );
        profile.setHeight( entity.getHeight() );
        profile.setWeight( entity.getWeight() );
        profile.setBirthday(entity.getBirthday());
        profile.setDietList(dietEntities);
        profile.setGoalList(goalEntities);
        profile.setPhysicalLoadLevelList(physicalLoadLevelEntities);
        profile.setSexList(sexEntities);
        return profile;
    }
}

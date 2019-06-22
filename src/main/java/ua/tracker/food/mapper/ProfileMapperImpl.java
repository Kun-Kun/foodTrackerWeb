package ua.tracker.food.mapper;

import ua.tracker.food.component.Gender;
import ua.tracker.food.component.UserParameters;
import ua.tracker.food.dto.Profile;
import ua.tracker.food.dto.ProfileSelectable;

import java.util.List;
import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.dao.entity.GoalEntity;
import ua.tracker.food.dao.entity.PhysicalLoadLevelEntity;
import ua.tracker.food.dao.entity.ProfileEntity;
import ua.tracker.food.tool.DataTransformTools;

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
        profile.setGender( entity.getGender() );
        profile.setHeight( entity.getHeight() );
        profile.setWeight( entity.getWeight() );
        profile.setBirthday(entity.getBirthday());
        return profile;
    }

    @Override
    public ProfileSelectable entityToDto(ProfileEntity entity, List<DietEntity> dietEntities, List<GoalEntity> goalEntities, List<PhysicalLoadLevelEntity> physicalLoadLevelEntities, List<Gender> genders) {
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
        profile.setGender( entity.getGender() );
        profile.setHeight( entity.getHeight() );
        profile.setWeight( entity.getWeight() );
        profile.setBirthday(entity.getBirthday());
        profile.setDietList(dietEntities);
        profile.setGoalList(goalEntities);
        profile.setPhysicalLoadLevelList(physicalLoadLevelEntities);
        profile.setGenderList(genders);
        return profile;
    }

    public UserParameters userParametersFromProfile(ProfileEntity entity){
        if ( entity == null ) {
            return null;
        }



        UserParameters userParameters = new UserParameters();
        userParameters.setGender(entity.getGender());
        if(entity.getBirthday() == null){
            userParameters.setAge(35);
        }else {
            userParameters.setAge(DataTransformTools.calculateAge(entity.getBirthday()));
        }
        userParameters.setHeight(entity.getHeight());
        userParameters.setWeight(entity.getWeight());
        return userParameters;
    }

}

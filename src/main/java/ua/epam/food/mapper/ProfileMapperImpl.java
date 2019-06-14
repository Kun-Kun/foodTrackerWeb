package ua.epam.food.mapper;

import ua.epam.food.dao.entity.ProfileEntity;
import ua.epam.food.dto.Profile;

public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public Profile entityToDto(ProfileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setId( entity.getId() );
        profile.setFirstName( entity.getFirstName() );
        profile.setLastName( entity.getLastName() );
        profile.setEmail( entity.getEmail() );
        profile.setGoal( entity.getGoal() );
        profile.setHeight( entity.getHeight() );
        profile.setWeight( entity.getWeight() );
        profile.setBirthday(entity.getBirthday());
        return profile;
    }
}

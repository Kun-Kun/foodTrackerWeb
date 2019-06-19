package ua.epam.food.services;

import ua.epam.food.dto.ProfileSelectable;
import ua.epam.food.exception.InvalidInputException;

public interface UserProfileService {
    void setValue(Integer userId, String fieldName, String value) throws InvalidInputException;
    ProfileSelectable getProfileByProfileId(Integer profileId);
}

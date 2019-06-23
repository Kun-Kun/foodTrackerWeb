package ua.tracker.food.services;

import ua.tracker.food.dto.ProfileSelectable;
import ua.tracker.food.exception.InvalidInputException;

public interface UserProfileService {
    void setValue(Integer userId, String fieldName, String value) throws InvalidInputException;
    ProfileSelectable getProfileSelectableByProfileId(Integer profileId);
}

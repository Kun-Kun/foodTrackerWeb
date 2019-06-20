package ua.tracker.food.services;

import ua.tracker.food.exception.InvalidInputException;

public interface UserRegistrationService {
    boolean registerUser(String username,String email,String password,String confirmPassword) throws InvalidInputException;
}

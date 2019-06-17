package ua.epam.food.services;

import ua.epam.food.exception.InvalidInputException;

public interface UserRegistrationService {
    boolean registerUser(String username,String email,String password,String confirmPassword) throws InvalidInputException;
}

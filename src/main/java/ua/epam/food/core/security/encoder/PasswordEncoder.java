package ua.epam.food.core.security.encoder;

public interface PasswordEncoder {

    String getHashedText(String text);

    boolean isHashedTextMatch(String text, String hashedText);
}

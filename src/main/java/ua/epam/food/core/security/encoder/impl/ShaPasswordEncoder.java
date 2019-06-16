package ua.epam.food.core.security.encoder.impl;

import ua.epam.food.core.security.encoder.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ShaPasswordEncoder implements PasswordEncoder {

    @Override
    public String getHashedText(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);

            return encoded;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean isHashedTextMatch(String text, String hashedText) {
        String tempHashedText = getHashedText(text);
        return tempHashedText.equals(hashedText);
    }
}
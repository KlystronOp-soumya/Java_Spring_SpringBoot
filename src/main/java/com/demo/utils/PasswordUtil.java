package com.demo.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class file to encode the password if it is having any special characters
public class PasswordUtil {

    public static final String getEncodedPassword(final String password) {
        //check if the password is having any special characters or not
        //if it has special characters then
        if (containsSpecialCharacters(password)) {
            return URLEncoder.encode(password, StandardCharsets.UTF_8);
        }
        return password;
    }

    protected final static boolean containsSpecialCharacters(String input) {
        // Regular expression to match special characters
        String regex = "[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find(); // Returns true if any special character is found
    }


}

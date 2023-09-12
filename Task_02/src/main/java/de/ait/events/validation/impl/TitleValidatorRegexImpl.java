package de.ait.events.validation.impl;

import de.ait.events.validation.TitleValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TitleValidatorRegexImpl implements TitleValidator {
    private static final String REGEX = "^[A-Za-z0-9]+$";

    @Override
    public void validate(String title) {
        if (title == null || title.isEmpty() || title.equals(" ")) {
            throw new IllegalArgumentException("Title mustn't be empty");
        }

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(title);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Title not format");
        }

    }
}

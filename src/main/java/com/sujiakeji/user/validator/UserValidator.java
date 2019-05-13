package com.sujiakeji.user.validator;

import com.sujiakeji.user.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String userName = user.getUserName();

        if (!isValidLength(userName, 1, 30)) {
            errors.rejectValue("userName", "user.userName.size");
        }
    }

    public boolean isValidLength(String input, Integer minLength, Integer maxLength) {
        return input != null && input.length() > minLength && input.length() < maxLength;
    }

    public boolean isValidRegex(String regex, String input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        return Pattern.compile(regex).matcher(input).matches();
    }

}
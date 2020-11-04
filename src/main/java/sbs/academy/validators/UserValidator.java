package sbs.academy.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sbs.academy.data.User;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Name must be between 2 and 150 characters long");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "mail.empty", "Email must be unique");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must be least 6 characters long");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.empty", "Enter age");

        User user = (User) o;
        if (!user.getAge().isEmpty()){
            int userAge = Integer.parseInt(user.getAge());
            if (userAge < 18) {
                errors.rejectValue("age", "toYoung", "Cant be under the age of 18");
            }
        }else {
            errors.reject("age");
        }

    }
}

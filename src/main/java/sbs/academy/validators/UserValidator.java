package sbs.academy.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sbs.academy.data.User;
import sbs.academy.repositories.UserRepository;

import java.util.List;

public class UserValidator implements Validator {

    private UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Name must be between 2 and 150 characters long");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "mail.empty", "Email must be unique");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must be least 6 characters long");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confrimpassword", "password.empty", "Password must be least 6 characters long");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.empty", "Enter age");

        User user = (User) o;
        if (!user.getAge().isEmpty()) {
            int userAge = Integer.parseInt(user.getAge());
            if (userAge < 18) {
                errors.rejectValue("age", "toYoung", "Cant be under the age of 18");
            }
        } else {
            errors.reject("age");
        }
        if (user.getPassword() != null && user.getConfrimpassword() != null) {
            if (!user.getPassword().equals(user.getConfrimpassword())) {
                errors.rejectValue("confrimpassword", "Password didn't match", "please match passwords");
            }
        }

        List<String> usersMail = userRepository.findAllByMail();
        for (String s : usersMail) {
            if (s.equals(user.getMail())) {
                errors.rejectValue("mail", "E-mail already taken");
            }
        }

        if (user.getName().length() < 2) {
            errors.rejectValue("name", "name is to short");
        }
    }


}


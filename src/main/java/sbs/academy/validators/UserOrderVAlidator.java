package sbs.academy.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sbs.academy.data.UserOrder;

public class UserOrderVAlidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserOrder.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}

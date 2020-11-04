package sbs.academy.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sbs.academy.data.Products;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Products.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}

package sbs.academy.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sbs.academy.data.Product;
import sbs.academy.repositories.ProductRepository;

public class ProductValidator implements Validator {

    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "name cant be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "category.empty", "category cant be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price.empty", "price cant be empty");

        Product product = (Product) o;
        int price = 0;
        try {
            String[] splitPrice = product.getPrice().split(",");
            int desimal = Integer.parseInt(splitPrice[1]);
            price = Integer.parseInt(splitPrice[0]);
            if (desimal >= 50){
                price++;
            }
        }catch (Exception e){
            errors.rejectValue("price", "price must be a number");
        }

        if (price < 0){
            errors.rejectValue("price", "price cant be negativ");
        }
    }
}

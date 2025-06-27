package org.example.ex1.Util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositivePriceValidator implements ConstraintValidator<PriceValid, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value > 0;
    }
}

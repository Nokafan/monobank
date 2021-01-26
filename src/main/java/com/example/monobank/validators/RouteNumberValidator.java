package com.example.monobank.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RouteNumberValidator implements ConstraintValidator<ValidRouteNumber, String> {
    private static String PATTERN = "[\\-\\/\\sa-zA-Z0-9А-ЯЁа-яё()]*";

    @Override
    public boolean isValid(String routeNumber, ConstraintValidatorContext context) {
        return routeNumber.matches(PATTERN);
    }
}

package com.example.monobank.validators;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusNameValidator implements ConstraintValidator<ValidStatusName, String> {
    private final List<String> listStatusNames = new ArrayList<>() {
        {
            add("NEW");
            add("IN_PROGRESS");
            add("ERROR");
            add("DONE");
        }
    };

    @Override
    public boolean isValid(String statusName, ConstraintValidatorContext context) {
        return listStatusNames.contains(statusName);
    }
}

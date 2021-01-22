package com.example.monobank.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = StatusNameValidator.class)
@Target(value = {ElementType.FIELD})
@Retention(value = RUNTIME)
public @interface ValidStatusName {

    String message() default "Not valid StatusName";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

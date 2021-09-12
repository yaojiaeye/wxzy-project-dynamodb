package com.wxzy.aws.dynamodb.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateVerification, String> {

    @Override
    public void initialize(DateVerification constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return org.apache.commons.validator.DateValidator.getInstance().isValid(value, "yyyy-MM-dd", false);
    }
}

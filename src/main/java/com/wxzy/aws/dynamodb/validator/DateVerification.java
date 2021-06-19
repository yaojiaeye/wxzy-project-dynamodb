package com.wxzy.aws.dynamodb.validator;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author yaojia
 */
@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateVerification {

    String message() default "invalid plant_date format of yyyy-mm-dd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.example.springbootvue3.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StateValidation.class)
public @interface State {
    String message() default "文章状态只能是: 已发布或者草稿";
    Class[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

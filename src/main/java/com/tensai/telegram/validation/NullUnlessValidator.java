package com.tensai.telegram.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class NullUnlessValidator implements ConstraintValidator<ValidateNullUnless, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        boolean isValid = true;
        Class<?> clazz = obj.getClass();

        // Disable the default class-level violation to use custom field-level violations
        context.disableDefaultConstraintViolation();

        for (Field field : clazz.getDeclaredFields()) {
            NullUnless annotation = field.getAnnotation(NullUnless.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    Object annotatedFieldValue = field.get(obj);
                    String dependentFieldName = annotation.field();
                    String expectedValue = annotation.value();

                    // Get the value of the field we depend on
                    Field dependentField = clazz.getDeclaredField(dependentFieldName);
                    dependentField.setAccessible(true);
                    Object actualDependentValue = dependentField.get(obj);

                    String actualDependentValueStr = actualDependentValue != null ?
                            actualDependentValue.toString() : "null";

                    // if the field has a value
                    if (annotatedFieldValue != null) {
                        // If the actual value doesn't match the expected value, validation fails
                        if (!expectedValue.equals(actualDependentValueStr)) {

                            // Error message
                            String message = "%s must be null when %s = %s".formatted(
                                    field.getName(), dependentFieldName, actualDependentValueStr);

                            // Bind the violation to the specific field that failed
                            context.buildConstraintViolationWithTemplate(message)
                                    .addPropertyNode(field.getName())
                                    .addConstraintViolation();

                            isValid = false;
                        }
                    }
                    else {
                        // If the actual value match the expected value but the field is null, validation fails
                        if (expectedValue.equals(actualDependentValueStr)) {

                            // Error message
                            String message = "%s mustn't be null when %s = %s".formatted(
                                    field.getName(), dependentFieldName, actualDependentValueStr);

                            // Bind the violation to the specific field that failed
                            context.buildConstraintViolationWithTemplate(message)
                                    .addPropertyNode(field.getName())
                                    .addConstraintViolation();

                            isValid = false;
                        }
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("Validation reflection error: Check field names in @NullUnless", e);
                }
            }
        }
        return isValid;
    }
}
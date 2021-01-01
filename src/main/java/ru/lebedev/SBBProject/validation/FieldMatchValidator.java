package ru.lebedev.SBBProject.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        try {
            final Object firstObj = new
                    BeanWrapperImpl(o).getPropertyValue(firstFieldName);
            final Object secondObj = new
                    BeanWrapperImpl(o).getPropertyValue(secondFieldName);
            valid = firstObj == null && secondObj == null || firstObj != null &&
                    firstObj.equals(secondObj);
        } catch (final Exception ignore) {
        }

        if (!valid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
package com.ciros.validation;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ciros.validation.constraints.MonthValue;

/**
 * @author Ciro Scognamiglio
 */

public class MonthValueValidator implements ConstraintValidator<MonthValue, CharSequence> {

    private List<CharSequence> enumValues;
    private List<CharSequence> acceptedValues;

    @Override
    public void initialize(MonthValue constraintAnnotation) {

        this.enumValues = Stream.of(Month.values()).map(Enum::name).collect(Collectors.toList());

        this.acceptedValues = Stream.of(constraintAnnotation.acceptedValues()).map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null)
            return true;

        context.disableDefaultConstraintViolation();

        if (acceptedValues.isEmpty()) {
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate() + enumValues)
                    .addConstraintViolation();
            return enumValues.contains(value);

        } else {
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate() + acceptedValues)
                    .addConstraintViolation();
            return acceptedValues.contains(value);
        }
    }

}
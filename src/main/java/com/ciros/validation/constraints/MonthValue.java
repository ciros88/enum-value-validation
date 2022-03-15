package com.ciros.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.Month;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ciros.validation.MonthValueValidator;

/**
 * through {@link #acceptedValues() acceptedValues} it is possible to define the
 * acceptable enum values
 * <p>
 * Accepts {@code CharSequence}.
 * <p>
 * {@code null} elements are considered valid.
 * 
 * @author Ciro Scognamiglio
 */

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MonthValueValidator.class)
public @interface MonthValue {

    Month[] acceptedValues() default {};

    String message() default "invalid value provided. Accepted values: ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

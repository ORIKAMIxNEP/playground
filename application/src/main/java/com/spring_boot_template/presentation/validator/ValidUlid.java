package com.spring_boot_template.presentation.validator;

import com.spring_boot_template.domain.exception.RequestInvalidException;
import com.spring_boot_template.presentation.controller.common.UlidRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UlidValidator.class)
public @interface ValidUlid {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

@RequiredArgsConstructor
class UlidValidator implements ConstraintValidator<ValidUlid, UlidRequest> {
    private final MessageSource messageSource;

    @Override
    public boolean isValid(
            final UlidRequest ulidRequest, final ConstraintValidatorContext context) {
        if (!ulidRequest.value().matches("^(?!.*[ILOU])[0-9A-Z]{26}$")) {
            final String code = "invalid-ulid-format";
            final String className = ulidRequest.getClass().getSimpleName();
            //            final String replacedClassName = className.replace("Request", "");
            final Object[] args = new Object[] {className};
            final Locale locale = Locale.getDefault();
            final String message = messageSource.getMessage(code, args, locale);
            throw new RequestInvalidException(message);
        }
        return true;
    }
}

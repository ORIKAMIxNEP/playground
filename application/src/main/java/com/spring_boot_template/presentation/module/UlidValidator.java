package com.spring_boot_template.presentation.module;

import com.spring_boot_template.domain.exception.RequestInvalidException;
import com.spring_boot_template.presentation.anotation.ValidUlid;
import com.spring_boot_template.presentation.controller.common.UlidRequest;
import com.spring_boot_template.shared.constants.MessageCode;
import com.spring_boot_template.shared.module.MessageGenerator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UlidValidator implements ConstraintValidator<ValidUlid, UlidRequest> {
    private final MessageGenerator messageGenerator;

    @Override
    public boolean isValid(
            final UlidRequest ulidRequest, final ConstraintValidatorContext context) {
        final String ulidRegex = "^(?!.*[ILOU])[0-9A-Z]{26}$";
        if (ulidRequest.value().matches(ulidRegex)) {
            return true;
        }
        final String message =
                messageGenerator.generateMessage(
                        MessageCode.INVALID_ULID_FORMAT, ulidRequest.getClass());
        throw new RequestInvalidException(message);
    }
}

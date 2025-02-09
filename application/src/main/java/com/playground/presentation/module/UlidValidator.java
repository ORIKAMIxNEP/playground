package com.playground.presentation.module;

import com.playground.domain.exception.RequestInvalidException;
import com.playground.presentation.anotation.ValidUlid;
import com.playground.presentation.controller.common.UlidRequest;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UlidValidator implements ConstraintValidator<ValidUlid, UlidRequest> {
  private final MessageGenerator messageGenerator;

  @Override
  public boolean isValid(final UlidRequest ulidRequest, final ConstraintValidatorContext context) {
    final String ulidRegex = "^(?!.*[ILOU])[0-9A-Z]{26}$";
    if (ulidRequest.value().matches(ulidRegex)) {
      return true;
    }
    final String message =
        messageGenerator.generateMessage(MessageCode.INVALID_ULID_FORMAT, ulidRequest.getClass());
    throw new RequestInvalidException(message);
  }
}

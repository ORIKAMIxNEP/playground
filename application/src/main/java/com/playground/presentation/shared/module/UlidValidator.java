package com.playground.presentation.shared.module;

import com.playground.domain.exception.RequestInvalidException;
import com.playground.presentation.anotation.ValidUlid;
import com.playground.presentation.controller.common.UlidRequest;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class UlidValidator implements ConstraintValidator<ValidUlid, UlidRequest> {
  private static final String ULID_REGEX = "^(?!.*[ILOU])[0-9A-Z]{26}$";

  private final MessageGenerator messageGenerator;

  @Override
  public boolean isValid(final UlidRequest ulidRequest, final ConstraintValidatorContext context) {
    if (ulidRequest.value().matches(ULID_REGEX)) {
      return true;
    }
    final String message =
        messageGenerator.generateMessage(MessageCode.INVALID_ULID_FORMAT, ulidRequest.getClass());
    throw new RequestInvalidException(message);
  }
}

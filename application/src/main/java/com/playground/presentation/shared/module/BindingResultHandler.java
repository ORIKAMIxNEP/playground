package com.playground.presentation.shared.module;

import com.playground.domain.exception.RequestInvalidException;
import com.playground.shared.module.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@RequiredArgsConstructor
public final class BindingResultHandler {
  private final MessageGenerator messageGenerator;

  public void handleBindingResult(final BindingResult bindingResult) {
    if (!bindingResult.hasErrors()) {
      return;
    }
    final FieldError fieldError = bindingResult.getFieldError();
    final String message = messageGenerator.generateMessage(fieldError);
    throw new RequestInvalidException(message);
  }
}

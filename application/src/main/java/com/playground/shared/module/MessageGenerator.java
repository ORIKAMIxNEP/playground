package com.playground.shared.module;

import com.playground.shared.constants.MessageCode;
import java.util.Locale;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class MessageGenerator {
  private final MessageSource messageSource;
  private final Locale locale = Locale.getDefault();

  public String generateMessage(
      final MessageCode messageCode, final Class<?> targetDomainObjectType) {
    final String code = messageCode.getValue();
    if (Objects.isNull(targetDomainObjectType)) {
      final Object[] args = new Object[] {};
      return messageSource.getMessage(code, args, locale);
    }
    final String className = targetDomainObjectType.getSimpleName();
    final Object[] args = new Object[] {className};
    return messageSource.getMessage(code, args, locale);
  }

  public String generateMessage(final MessageSourceResolvable messageSourceResolvable) {
    return messageSource.getMessage(messageSourceResolvable, locale);
  }
}

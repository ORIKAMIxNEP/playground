package com.spring_boot_template.presentation.handler;

import lombok.Builder;
import lombok.Value;
import org.springframework.validation.BindingResult;

@Value
@Builder
public class HttpClientErrorHandlerRequest {
  String receivedCsrfToken;
  @Builder.Default BindingResult bindingResult = null;
}

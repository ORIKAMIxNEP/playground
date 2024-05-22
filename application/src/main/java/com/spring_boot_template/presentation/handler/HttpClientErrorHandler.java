package com.spring_boot_template.presentation.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@RequiredArgsConstructor
public class HttpClientErrorHandler {
  @Value("${csrf.token}")
  private String configuredCsrfToken;

  // HTTPクライアントエラー処理
  public HttpClientErrorHandlerResponse handle(
      HttpClientErrorHandlerRequest httpClientErrorHandlerRequest) {
    final String receivedCsrfToken = httpClientErrorHandlerRequest.getReceivedCsrfToken();
    final BindingResult bindingResult = httpClientErrorHandlerRequest.getBindingResult();

    // CSRFトークンが異なる場合
    if (!configuredCsrfToken.equals(receivedCsrfToken)) {
      return HttpClientErrorHandlerResponse.builder()
          .error(true)
          .responseEntity(
              ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid CSRF Token Error"))
          .build();
    }

    // バリデーションエラーがある場合
    if (bindingResult != null && bindingResult.hasErrors()) {
      return HttpClientErrorHandlerResponse.builder()
          .error(true)
          .responseEntity(ResponseEntity.badRequest().body(bindingResult.getAllErrors()))
          .build();
    }

    return HttpClientErrorHandlerResponse.builder().error(false).responseEntity(null).build();
  }
}

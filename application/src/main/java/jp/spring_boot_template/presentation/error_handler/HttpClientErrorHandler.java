package jp.spring_boot_template.presentation.error_handler;

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
  private String csrfToken;

  // HTTPクライアントエラー処理
  public HttpClientErrorHandlerResponse handle(
      final String clientCsrfToken, final BindingResult bindingResult) {

    // CSRFトークンが異なる場合
    if (!csrfToken.equals(clientCsrfToken)) {
      return new HttpClientErrorHandlerResponse(
          true, ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid CSRF Token Error"));
    }

    // バリデーションエラーがあった場合
    if (bindingResult != null && bindingResult.hasErrors()) {
      return new HttpClientErrorHandlerResponse(
          true, ResponseEntity.badRequest().body(bindingResult.getAllErrors()));
    }

    return new HttpClientErrorHandlerResponse(false, null);
  }
}

package com.playground.presentation.authentication;

import com.playground.presentation.shared.module.AuthenticationManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class LogoutController {
  private final AuthenticationManager authenticationManager;

  @DeleteMapping(value = "/authentication")
  @Operation(
      tags = {"authentication"},
      summary = "ログアウトする",
      responses = {
        @ApiResponse(responseCode = "204", description = "No Content"),
        @ApiResponse(responseCode = "403", description = "Forbidden")
      })
  public ResponseEntity<Void> execute(final HttpServletRequest httpServletRequest) {
    authenticationManager.clearAuthentication(httpServletRequest);
    return ResponseEntity.noContent().build();
  }
}

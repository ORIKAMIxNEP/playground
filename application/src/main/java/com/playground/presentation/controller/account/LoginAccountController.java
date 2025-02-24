package com.playground.presentation.controller.account;

import com.playground.application.account.LoginAccountUseCase;
import com.playground.domain.exception.RequestInvalidException;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.presentation.controller.account.request.LoginAccountRequest;
import com.playground.presentation.shared.module.AccountSessionManager;
import com.playground.presentation.shared.module.BindingResultHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class LoginAccountController {
  private final LoginAccountUseCase loginAccountUseCase;
  private final BindingResultHandler bindingResultHandler;
  private final AccountSessionManager accountSessionManager;

  @PostMapping(value = "/account/credential", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      tags = {"account"},
      summary = "アカウントにログインする",
      responses = {
        @ApiResponse(responseCode = "204", description = "No Content"),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(oneOf = {RequestInvalidException.class}))),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(oneOf = {ResourceNotFoundException.class})))
      })
  private ResponseEntity<String> execute(
      @RequestBody @Validated final LoginAccountRequest loginAccountRequest,
      final BindingResult bindingResult,
      final HttpServletRequest httpServletRequest) {
    bindingResultHandler.handleBindingResult(bindingResult);
    final String accountId = loginAccountUseCase.execute(loginAccountRequest);
    accountSessionManager.generateAccountSession(httpServletRequest, accountId);
    return ResponseEntity.noContent().build();
  }
}

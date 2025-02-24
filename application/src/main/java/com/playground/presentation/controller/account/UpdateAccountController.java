package com.playground.presentation.controller.account;

import com.playground.application.account.UpdateAccountUseCase;
import com.playground.domain.exception.DomainRuleViolationException;
import com.playground.domain.exception.RequestInvalidException;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.presentation.controller.account.request.UpdateAccountRequest;
import com.playground.presentation.shared.dto.SessionAccountId;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class UpdateAccountController {
  private final UpdateAccountUseCase updateAccountUseCase;
  private final BindingResultHandler bindingResultHandler;
  private final AccountSessionManager accountSessionManager;

  @PutMapping(value = "/account", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      tags = {"account"},
      summary = "アカウントを更新する",
      responses = {
        @ApiResponse(responseCode = "204", description = "No Content"),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(oneOf = {RequestInvalidException.class}))),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(oneOf = {ResourceNotFoundException.class}))),
        @ApiResponse(
            responseCode = "422",
            description = "Unprocessable Entity",
            content = @Content(schema = @Schema(oneOf = {DomainRuleViolationException.class})))
      })
  private ResponseEntity<?> execute(
      @RequestBody @Validated final UpdateAccountRequest updateAccountRequest,
      final BindingResult bindingResult,
      final HttpServletRequest httpServletRequest) {
    bindingResultHandler.handleBindingResult(bindingResult);
    final SessionAccountId sessionAccountId =
        accountSessionManager.fetchAccountId(httpServletRequest);
    updateAccountUseCase.execute(sessionAccountId, updateAccountRequest);
    return ResponseEntity.noContent().build();
  }
}

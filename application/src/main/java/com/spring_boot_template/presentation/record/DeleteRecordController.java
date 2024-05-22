package com.spring_boot_template.presentation.record;

import com.spring_boot_template.application.record.impl.DeleteRecordUseCaseImpl;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandler;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandlerRequest;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandlerResponse;
import com.spring_boot_template.presentation.record.request.DeleteRecordRequest;
import com.spring_boot_template.presentation.record.response.DeleteRecordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class DeleteRecordController {
  private final HttpClientErrorHandler httpClientErrorHandler;
  private final DeleteRecordUseCaseImpl deleteRecordUseCaseImpl;

  @DeleteMapping(value = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードを削除する",
      description = "レコードIDを受け取る" + " → レコードを削除する" + " → 成功したかを返す",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DeleteRecordResponse.class)))
      })
  public ResponseEntity<?> deleteRecord(
      @RequestHeader("X-CSRF-Token") String receivedCsrfToken,
      @RequestBody @Validated final DeleteRecordRequest deleteRecordRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(
            HttpClientErrorHandlerRequest.builder()
                .receivedCsrfToken(receivedCsrfToken)
                .bindingResult(bindingResult)
                .build());
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(deleteRecordUseCaseImpl.execute(deleteRecordRequest));
  }
}

package com.spring_boot_template.presentation.record;

import com.spring_boot_template.application.record.impl.AddRecordUseCaseImpl;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandler;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandlerRequest;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandlerResponse;
import com.spring_boot_template.presentation.record.request.AddRecordRequest;
import com.spring_boot_template.presentation.record.response.AddRecordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class AddRecordController {
  private final HttpClientErrorHandler httpClientErrorHandler;
  private final AddRecordUseCaseImpl addRecordUseCaseImpl;

  @PostMapping(value = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードを追加する",
      description = "カラム1、カラム2を受け取る" + " → レコードを追加する" + " → 成功したかを返す",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AddRecordResponse.class)))
      })
  public ResponseEntity<?> addRecord(
      @RequestHeader("X-CSRF-Token") String receivedCsrfToken,
      @RequestBody @Validated final AddRecordRequest addRecordRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(
            HttpClientErrorHandlerRequest.builder()
                .receivedCsrfToken(receivedCsrfToken)
                .bindingResult(bindingResult)
                .build());
    if (httpClientErrorHandlerResponse.hasError()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(addRecordUseCaseImpl.execute(addRecordRequest));
  }
}

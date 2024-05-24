package com.spring_boot_template.presentation.record;

import com.spring_boot_template.application.record.impl.FetchRecordUseCaseImpl;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandler;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandlerRequest;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandlerResponse;
import com.spring_boot_template.presentation.record.response.FetchRecordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class FetchRecordController {
  private final HttpClientErrorHandler httpClientErrorHandler;
  private final FetchRecordUseCaseImpl fetchRecordUseCaseImpl;

  @GetMapping("/record")
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードを取得する",
      description = "レコードIDを受け取る" + " → レコードを取得する" + " → レコードID、カラム1、カラム2を返す",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FetchRecordResponse.class)))
      })
  public ResponseEntity<?> fetchRecord(
      @RequestHeader("X-CSRF-Token") String receivedCsrfToken,
      @RequestParam @Validated final long recordId) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(
            HttpClientErrorHandlerRequest.builder().receivedCsrfToken(receivedCsrfToken).build());
    if (httpClientErrorHandlerResponse.hasError()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(fetchRecordUseCaseImpl.execute(recordId));
  }
}

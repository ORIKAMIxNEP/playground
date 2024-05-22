package com.spring_boot_template.presentation.record;

import com.spring_boot_template.application.record.impl.AddRecordUseCaseImpl;
import com.spring_boot_template.application.record.impl.DeleteRecordUseCaseImpl;
import com.spring_boot_template.application.record.impl.FetchRecordUseCaseImpl;
import com.spring_boot_template.application.record.impl.UpdateRecordColumn1UseCaseImpl;
import com.spring_boot_template.application.record.impl.UpdateRecordUseCaseImpl;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandler;
import com.spring_boot_template.presentation.handler.HttpClientErrorHandlerResponse;
import com.spring_boot_template.presentation.record.request.AddRecordRequest;
import com.spring_boot_template.presentation.record.request.DeleteRecordRequest;
import com.spring_boot_template.presentation.record.request.FetchRecordRequest;
import com.spring_boot_template.presentation.record.request.HogeRecordRequest;
import com.spring_boot_template.presentation.record.request.UpdateRecordColumn1Request;
import com.spring_boot_template.presentation.record.request.UpdateRecordRequest;
import com.spring_boot_template.presentation.record.response.AddRecordResponse;
import com.spring_boot_template.presentation.record.response.DeleteRecordResponse;
import com.spring_boot_template.presentation.record.response.FetchRecordResponse;
import com.spring_boot_template.presentation.record.response.HogeRecordResponse;
import com.spring_boot_template.presentation.record.response.UpdateRecordColumn1Response;
import com.spring_boot_template.presentation.record.response.UpdateRecordResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public final class RecordController {
  private final HttpClientErrorHandler httpClientErrorHandler;
  private final AddRecordUseCaseImpl addRecordUseCaseImpl;
  private final FetchRecordUseCaseImpl fetchRecordUseCaseImpl;
  private final UpdateRecordUseCaseImpl updateRecordUseCaseImpl;
  private final UpdateRecordColumn1UseCaseImpl updateRecordColumn1UseCaseImpl;
  private final DeleteRecordUseCaseImpl deleteRecordUseCaseImpl;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final AddRecordRequest addRecordRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(addRecordUseCaseImpl.execute(addRecordRequest));
  }

  @GetMapping()
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
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final FetchRecordRequest fetchRecordRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, null);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(fetchRecordUseCaseImpl.execute(fetchRecordRequest));
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードを更新する",
      description = "レコードID、カラム1、カラム2を受け取る" + " → レコードを更新する" + " → 成功したかを返す",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateRecordResponse.class)))
      })
  public ResponseEntity<?> updateRecord(
      @RequestHeader("X-CSRF-TOKEN") String clientCsrfToken,
      @RequestBody @Validated final UpdateRecordRequest updateRecordRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(updateRecordUseCaseImpl.execute(updateRecordRequest));
  }

  @PatchMapping(value = "/column1", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードカラム1を更新する",
      description = "レコードID、カラム1を受け取る" + " → レコードカラム1を更新する" + " → 成功したかを返す",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateRecordColumn1Response.class)))
      })
  public ResponseEntity<?> updateRecordColumn1(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final UpdateRecordColumn1Request updateRecordColumn1Request,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(updateRecordColumn1UseCaseImpl.execute(updateRecordColumn1Request));
  }

  @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final DeleteRecordRequest deleteRecordRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(deleteRecordUseCaseImpl.execute(deleteRecordRequest));
  }

  @PostMapping(value = "/hoge", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードをhogeする",
      description = "fugaを受け取る" + " → レコードをhogeする" + " → piyoを返す",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = HogeRecordResponse.class)))
      })
  public ResponseEntity<?> hogeRecord(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final HogeRecordRequest hogeRecordRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(
        HogeRecordResponse.builder().recordId(hogeRecordRequest.recordId()).build());
  }
}

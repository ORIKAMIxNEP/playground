package com.spring_boot_template.presentation.record;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.spring_boot_template.application.dto.record.AddRecordInput;
import com.spring_boot_template.application.dto.record.AddRecordOutput;
import com.spring_boot_template.application.dto.record.DeleteRecordInput;
import com.spring_boot_template.application.dto.record.DeleteRecordOutput;
import com.spring_boot_template.application.dto.record.FetchRecordOutput;
import com.spring_boot_template.application.dto.record.UpdateRecordColumn1Input;
import com.spring_boot_template.application.dto.record.UpdateRecordColumn1Output;
import com.spring_boot_template.application.dto.record.UpdateRecordInput;
import com.spring_boot_template.application.dto.record.UpdateRecordOutput;
import com.spring_boot_template.application.record.impl.RecordUseCaseImpl;
import com.spring_boot_template.presentation.error_handler.HttpClientErrorHandler;
import com.spring_boot_template.presentation.error_handler.HttpClientErrorHandlerResponse;
import com.spring_boot_template.presentation.record.request.AddRecordRequest;
import com.spring_boot_template.presentation.record.request.DeleteRecordRequest;
import com.spring_boot_template.presentation.record.request.HogeRecordRequest;
import com.spring_boot_template.presentation.record.request.UpdateRecordColumn1Request;
import com.spring_boot_template.presentation.record.request.UpdateRecordRequest;
import com.spring_boot_template.presentation.record.response.AddRecordResponse;
import com.spring_boot_template.presentation.record.response.DeleteRecordResponse;
import com.spring_boot_template.presentation.record.response.FetchRecordResponse;
import com.spring_boot_template.presentation.record.response.HogeRecordResponse;
import com.spring_boot_template.presentation.record.response.UpdateRecordColumn1Response;
import com.spring_boot_template.presentation.record.response.UpdateRecordResponse;
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
public class RecordController {
  private final HttpClientErrorHandler httpClientErrorHandler;
  private final RecordUseCaseImpl recordUseCaseImpl;

  // レコード追加
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードを追加する",
      description = "カラム1、カラム2を受け取ってレコードを追加する",
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
    final AddRecordOutput addRecordOutput =
        recordUseCaseImpl.addRecord(
            AddRecordInput.builder()
                .column1(addRecordRequest.column1())
                .column2(addRecordRequest.column2())
                .build());
    return ResponseEntity.ok(
        AddRecordResponse.builder().success(addRecordOutput.success()).build());
  }

  // レコード取得
  @GetMapping()
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードを取得する",
      description = "レコードを取得してレコードID、カラム1、カラム2を返す",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FetchRecordResponse.class)))
      })
  public ResponseEntity<?> fetchRecord(@RequestHeader("X-CSRF-Token") String clientCsrfToken) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, null);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    final FetchRecordOutput fetchRecordOutput = recordUseCaseImpl.fetchRecord();
    return ResponseEntity.ok(
        FetchRecordResponse.builder()
            .column1(fetchRecordOutput.column1())
            .column2(fetchRecordOutput.column2())
            .build());
  }

  // レコード更新
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードを更新する",
      description = "レコードID、カラム1、カラム2を受け取ってレコードを更新する",
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
    final UpdateRecordOutput updateRecordOutput =
        recordUseCaseImpl.updateRecord(
            UpdateRecordInput.builder()
                .recordId(updateRecordRequest.recordId())
                .column1(updateRecordRequest.column1())
                .column2(updateRecordRequest.column2())
                .build());
    return ResponseEntity.ok(UpdateRecordResponse.builder().success(updateRecordOutput.success()));
  }

  // レコードカラム1更新
  @PatchMapping(value = "/column1", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードカラム1を追加する",
      description = "レコードID、カラム1を受け取ってレコードを更新する",
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
    final UpdateRecordColumn1Output updateRecordColumn1Output =
        recordUseCaseImpl.updateRecordColumn1(
            UpdateRecordColumn1Input.builder()
                .recordId(updateRecordColumn1Request.recordId())
                .column1(updateRecordColumn1Request.column1())
                .build());
    return ResponseEntity.ok(
        UpdateRecordColumn1Response.builder().success(updateRecordColumn1Output.success()));
  }

  // レコード削除
  @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードを削除する",
      description = "レコードIDを受け取ってレコードを削除する",
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
    final DeleteRecordOutput deleteRecordOutput =
        recordUseCaseImpl.deleteRecord(
            DeleteRecordInput.builder().recordId(deleteRecordRequest.recordId()).build());
    return ResponseEntity.ok(DeleteRecordResponse.builder().success(deleteRecordOutput.success()));
  }

  // レコードhoge
  @PostMapping(value = "/hoge", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @Operation(
      tags = {"record"},
      summary = "レコードをhogeする",
      description = "レコードをhogeする",
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

package jp.spring_boot_template.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jp.spring_boot_template.application.dto.record.*;
import jp.spring_boot_template.application.usecase.record.RecordUseCaseImpl;
import jp.spring_boot_template.presentation.controller.dto.*;
import jp.spring_boot_template.presentation.error_handler.HttpClientErrorHandler;
import jp.spring_boot_template.presentation.error_handler.HttpClientErrorHandlerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
                    schema = @Schema(implementation = AddResponse.class)))
      })
  public ResponseEntity<?> addRecord(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final AddRequest addRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    final AddOutput addOutput =
        recordUseCaseImpl.addRecord(
            AddInput.builder().column1(addRequest.column1()).column2(addRequest.column2()).build());
    return ResponseEntity.ok(AddResponse.builder().success(addOutput.success()).build());
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
                    schema = @Schema(implementation = FetchResponse.class)))
      })
  public ResponseEntity<?> fetchRecord(@RequestHeader("X-CSRF-Token") String clientCsrfToken) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, null);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    final FetchOutput fetchOutput = recordUseCaseImpl.fetchRecord();
    return ResponseEntity.ok(
        FetchResponse.builder()
            .column1(fetchOutput.column1())
            .column2(fetchOutput.column2())
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
                    schema = @Schema(implementation = UpdateResponse.class)))
      })
  public ResponseEntity<?> updateRecord(
      @RequestHeader("X-CSRF-TOKEN") String clientCsrfToken,
      @RequestBody @Validated final UpdateRequest updateRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    final UpdateOutput updateOutput =
        recordUseCaseImpl.updateRecord(
            UpdateInput.builder()
                .recordId(updateRequest.recordId())
                .column1(updateRequest.column1())
                .column2(updateRequest.column2())
                .build());
    return ResponseEntity.ok(UpdateResponse.builder().success(updateOutput.success()));
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
                    schema = @Schema(implementation = UpdateColumn1Response.class)))
      })
  public ResponseEntity<?> updateRecordColumn1(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final UpdateColumn1Request updateColumn1Request,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    final UpdateColumn1Output updateColumn1Output =
        recordUseCaseImpl.updateRecordColumn1(
            UpdateColumn1Input.builder()
                .recordId(updateColumn1Request.recordId())
                .column1(updateColumn1Request.column1())
                .build());
    return ResponseEntity.ok(
        UpdateColumn1Response.builder().success(updateColumn1Output.success()));
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
                    schema = @Schema(implementation = DeleteResponse.class)))
      })
  public ResponseEntity<?> deleteRecord(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final DeleteRequest deleteRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    final DeleteOutput deleteOutput =
        recordUseCaseImpl.deleteRecord(
            DeleteInput.builder().recordId(deleteRequest.recordId()).build());
    return ResponseEntity.ok(DeleteResponse.builder().success(deleteOutput.success()));
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
                    schema = @Schema(implementation = BlueprintResponse.class)))
      })
  public ResponseEntity<?> hoge(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final BlueprintRequest blueprintRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(
        BlueprintResponse.builder().recordId(blueprintRequest.recordId()).build());
  }
}

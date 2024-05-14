package jp.spring_boot_template.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jp.spring_boot_template.application.dto.AddInput;
import jp.spring_boot_template.application.dto.DeleteInput;
import jp.spring_boot_template.application.dto.DeleteOutput;
import jp.spring_boot_template.application.dto.FetchInput;
import jp.spring_boot_template.application.dto.UpdateColumn1Input;
import jp.spring_boot_template.application.dto.UpdateColumn1Output;
import jp.spring_boot_template.application.dto.UpdateInput;
import jp.spring_boot_template.application.dto.UpdateOutput;
import jp.spring_boot_template.application.usecase.RecordUseCaseImpl;
import jp.spring_boot_template.presentation.controller.dto.AddRequest;
import jp.spring_boot_template.presentation.controller.dto.AddResponse;
import jp.spring_boot_template.presentation.controller.dto.DeleteRequest;
import jp.spring_boot_template.presentation.controller.dto.FetchRequest;
import jp.spring_boot_template.presentation.controller.dto.FetchResponse;
import jp.spring_boot_template.presentation.controller.dto.UpdateColumn1Request;
import jp.spring_boot_template.presentation.controller.dto.UpdateRequest;
import jp.spring_boot_template.presentation.error_handler.HttpClientErrorHandler;
import jp.spring_boot_template.presentation.error_handler.HttpClientErrorHandlerResponse;
import lombok.RequiredArgsConstructor;
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
  @PostMapping
  @ResponseBody
  @Operation(
      summary = "レコードを追加する",
      tags = {"record"},
      description = "カラム1、カラム2を送信してレコードを追加する",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AddResponse.class)))
      })
  public ResponseEntity<?> add(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final AddRequest addRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }

    return ResponseEntity.ok(
        recordUseCaseImpl.add(
            AddInput.builder()
                .column1(addRequest.column1())
                .column2(addRequest.column2())
                .build()));
  }

  // レコード取得
  @GetMapping
  @ResponseBody
  @Operation(
      summary = "レコードを取得する",
      tags = {"record"},
      description = "レコードを取得してレコードID、カラム1、カラム2を受信する",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FetchResponse.class)))
      })
  public ResponseEntity<?> fetch(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final FetchRequest fetchRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(
        recordUseCaseImpl.fetch(FetchInput.builder().recordId(fetchRequest.recordId()).build()));
  }

  // レコード更新
  @PutMapping
  @ResponseBody
  @Operation(
      summary = "レコードを更新する",
      tags = {"record"},
      description = "レコードID、カラム1、カラム2を送信してレコードを更新する",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateOutput.class)))
      })
  public ResponseEntity<?> update(
      @RequestHeader("X-CSRF-TOKEN") String clientCsrfToken,
      @RequestBody @Validated final UpdateRequest updateRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(
        recordUseCaseImpl.update(
            UpdateInput.builder()
                .recordId(updateRequest.recordId())
                .column1(updateRequest.column1())
                .column2(updateRequest.column2())
                .build()));
  }

  // レコードカラム1更新
  @PatchMapping("/column1")
  @ResponseBody
  @Operation(
      summary = "レコードカラム1を追加する",
      tags = {"record"},
      description = "レコードID、カラム1を送信してレコードを更新する",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UpdateColumn1Output.class)))
      })
  public ResponseEntity<?> updateColumn1(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final UpdateColumn1Request updateColumn1Request,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(
        recordUseCaseImpl.updateColumn1(
            UpdateColumn1Input.builder()
                .recordId(updateColumn1Request.recordId())
                .column1(updateColumn1Request.column1())
                .build()));
  }

  // レコード削除
  @DeleteMapping
  @ResponseBody
  @Operation(
      summary = "レコードを削除する",
      tags = {"record"},
      description = "レコードIDを送信してレコードを削除する",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DeleteOutput.class)))
      })
  public ResponseEntity<?> delete(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final DeleteRequest deleteRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(
        recordUseCaseImpl.delete(DeleteInput.builder().recordId(deleteRequest.recordId()).build()));
  }
}

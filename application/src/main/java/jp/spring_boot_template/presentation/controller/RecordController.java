package jp.spring_boot_template.presentation.controller;

import jp.spring_boot_template.application.dto.record.AddRequest;
import jp.spring_boot_template.application.dto.record.UpdateRecord1Request;
import jp.spring_boot_template.application.dto.record.UpdateRequest;
import jp.spring_boot_template.application.usecase.RecordUseCase;
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
  private final RecordUseCase recordUseCase;

  // レコード追加
  @PostMapping
  @ResponseBody
  @Operation(summary = "レコードを追加する", tags = {"record"}, description = "カラム1、カラム2を送信してレコードを追加する",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListTaskResponseDto.class)))})
  public ResponseEntity<?> add(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final AddRequest addRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordUseCase.add(addRequest));
  }

  // レコード取得
  @GetMapping
  @ResponseBody
  @Operation(summary = "レコードを取得する", tags = {"record"}, description = "レコードを取得してレコードID、カラム1、カラム2を受信する",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListTaskResponseDto.class)))})
  public ResponseEntity<?> fetch(@RequestHeader("X-CSRF-Token") String clientCsrfToken) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, null);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordUseCase.fetch());
  }

  // レコード更新
  @PutMapping
  @ResponseBody
  @Operation(summary = "レコードを更新する", tags = {"record"}, description = "レコードID、カラム1、カラム2を送信してレコードを更新する",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListTaskResponseDto.class)))})
  public ResponseEntity<?> update(
      @RequestHeader("X-CSRF-TOKEN") String clientCsrfToken,
      @RequestBody @Validated final UpdateRequest updateRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordUseCase.update(updateRequest));
  }

  // レコードレコード1更新
  @PatchMapping("/record1")
  @ResponseBody
  @Operation(summary = "レコードカラム1を追加する", tags = {"record"}, description = "レコードID、カラム1を送信してレコードを更新する",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListTaskResponseDto.class)))})
  public ResponseEntity<?> updateRecord1(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final UpdateRecord1Request updateRecord1Request,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordUseCase.updateRecord1(updateRecord1Request));
  }

  // レコード削除
  @DeleteMapping
  @ResponseBody
  @Operation(summary = "レコードを削除する", tags = {"record"}, description = "レコードIDを送信してレコードを削除する",
            responses = {@ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListTaskResponseDto.class)))})
  public ResponseEntity<?> delete(@RequestHeader("X-CSRF-Token") String clientCsrfToken) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, null);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordUseCase.delete());
  }
}

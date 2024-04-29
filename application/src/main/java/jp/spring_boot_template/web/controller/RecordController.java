package jp.spring_boot_template.web.controller;

import jp.spring_boot_template.application.service.RecordService;
import jp.spring_boot_template.application.utility.HttpClientErrorHandler;
import jp.spring_boot_template.domain.dto.record.AddRequest;
import jp.spring_boot_template.domain.dto.record.UpdateRecord1Request;
import jp.spring_boot_template.domain.dto.record.UpdateRequest;
import jp.spring_boot_template.domain.dto.utility.HttpClientErrorHandlerResponse;
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
  private final RecordService recordService;

  // レコード追加
  @PostMapping
  @ResponseBody
  public ResponseEntity<?> add(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final AddRequest addRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordService.add(addRequest));
  }

  // レコード取得
  @GetMapping
  @ResponseBody
  public ResponseEntity<?> update(@RequestHeader("X-CSRF-Token") String clientCsrfToken) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, null);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
//    return ResponseEntity.ok(recordService.fetch());
      return ResponseEntity.ok(0);
  }

  // レコード更新
  @PutMapping
  @ResponseBody
  public ResponseEntity<?> update(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final UpdateRequest updateRequest,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordService.update(updateRequest));
  }

  // レコード1更新
  @PatchMapping("/record1")
  @ResponseBody
  public ResponseEntity<?> updateRecord1(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final UpdateRecord1Request updateRecord1Request,
      final BindingResult bindingResult) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordService.updateRecord1(updateRecord1Request));
  }

  // レコード削除
  @DeleteMapping
  @ResponseBody
  public ResponseEntity<?> delete(@RequestHeader("X-CSRF-Token") String clientCsrfToken) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, null);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(recordService.delete());
  }
}

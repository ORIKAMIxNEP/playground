package com.playground.presentation.controller.project;

import com.playground.application.project.FetchProjectsUseCase;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.presentation.controller.project.response.FetchProjectsResponse;
import com.playground.presentation.shared.dto.AuthenticatedAccountId;
import com.playground.presentation.shared.module.AuthenticationManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class FetchProjectsController {
  private final FetchProjectsUseCase fetchProjectsUseCase;
  private final AuthenticationManager authenticationManager;

  @GetMapping(value = "/projects")
  @ResponseBody
  @Operation(
      tags = {"project"},
      summary = "プロジェクトリストを取得する",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FetchProjectsResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(oneOf = {ResourceNotFoundException.class})))
      })
  public ResponseEntity<FetchProjectsResponse> execute() {
    final AuthenticatedAccountId authenticatedAccountId = authenticationManager.fetchAccountId();
    final FetchProjectsResponse fetchProjectsResponse =
        fetchProjectsUseCase.execute(authenticatedAccountId);
    return ResponseEntity.ok(fetchProjectsResponse);
  }
}

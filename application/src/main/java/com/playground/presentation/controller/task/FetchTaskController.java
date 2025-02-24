package com.playground.presentation.controller.task;

import com.playground.application.task.FetchTaskUseCase;
import com.playground.domain.exception.RequestInvalidException;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.presentation.anotation.ValidUlid;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import com.playground.presentation.controller.task.response.FetchTaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class FetchTaskController {
  private final FetchTaskUseCase fetchTaskUseCase;

  @GetMapping(value = "/project/{projectIdRequest}/task/{taskIdRequest}")
  @ResponseBody
  @Operation(
      tags = {"task"},
      summary = "タスクを取得する",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FetchTaskResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(oneOf = {RequestInvalidException.class}))),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(oneOf = {ResourceNotFoundException.class})))
      })
  private ResponseEntity<?> execute(
      @PathVariable @ValidUlid final ProjectIdRequest projectIdRequest,
      @PathVariable @ValidUlid final TaskIdRequest taskIdRequest) {
    return ResponseEntity.ok(fetchTaskUseCase.execute(projectIdRequest, taskIdRequest));
  }
}

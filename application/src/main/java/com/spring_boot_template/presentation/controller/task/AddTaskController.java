package com.spring_boot_template.presentation.controller.task;

import com.spring_boot_template.application.task.AddTaskUseCase;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import com.spring_boot_template.presentation.controller.task.request.TaskRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class AddTaskController {
    private final AddTaskUseCase addTaskUseCase;

    @PostMapping(
            value = "/project/{projectIdRequest}/task",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            tags = {"task"},
            summary = "タスクを追加する",
            responses = {
                @ApiResponse(responseCode = "204", description = "No Content"),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        oneOf = {
                                                            MethodArgumentNotValidException.class
                                                        }))),
                @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content =
                                @Content(
                                        schema =
                                                @Schema(oneOf = {ResourceNotFoundException.class})))
            })
    public ResponseEntity<String> execute(
            @PathVariable
                    @Schema(
                            title = "プロジェクトID",
                            type = "string",
                            minLength = 26,
                            maxLength = 26,
                            example = "1123456789ABCDEFGHJKMNPQRS")
                    final String projectIdRequest,
            final TaskRequest taskRequest) {
        addTaskUseCase.execute(projectIdRequest, taskRequest);

        return ResponseEntity.noContent().build();
    }
}

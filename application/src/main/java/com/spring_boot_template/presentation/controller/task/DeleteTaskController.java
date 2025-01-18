package com.spring_boot_template.presentation.controller.task;

import com.spring_boot_template.application.task.DeleteTaskUseCase;
import com.spring_boot_template.domain.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class DeleteTaskController {
    private final DeleteTaskUseCase deleteTaskUseCase;

    @DeleteMapping(value = "/project/{projectIdRequest}/task/{taskIdRequest}")
    @Operation(
            tags = {"task"},
            summary = "タスクを削除する",
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
    private ResponseEntity<?> execute(
            @PathVariable
                    @Schema(
                            title = "プロジェクトID",
                            type = "string",
                            minLength = 26,
                            maxLength = 26,
                            example = "1123456789ABCDEFGHJKMNPQRS")
                    final String projectIdRequest,
            @PathVariable
                    @Schema(
                            title = "タスクID",
                            type = "string",
                            minLength = 26,
                            maxLength = 26,
                            example = "2123456789ABCDEFGHJKMNPQRS")
                    String taskIdRequest) {
        deleteTaskUseCase.execute(projectIdRequest, taskIdRequest);

        return ResponseEntity.noContent().build();
    }
}

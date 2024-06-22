package com.spring_boot_template.presentation.controller.project;

import com.spring_boot_template.application.project.FetchTaskUseCase;
import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.project.response.FetchTaskResponse;
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

    @GetMapping("/task/{taskId}")
    @ResponseBody
    @Operation(
            tags = {"task"},
            summary = "タスクを取得する",
            description = "タスクIDを受け取る" + " → タスクを取得する" + " → タスク名、ステータス、担当アカウントID、詳細締め切り期日を返す",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema =
                                                @Schema(implementation = FetchTaskResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        oneOf = {
                                                            ValidationException.class,
                                                            DomainException.class
                                                        })))
            })
    private ResponseEntity<?> execute(@PathVariable final String taskId) {
        return ResponseEntity.ok(fetchTaskUseCase.execute(taskId));
    }
}

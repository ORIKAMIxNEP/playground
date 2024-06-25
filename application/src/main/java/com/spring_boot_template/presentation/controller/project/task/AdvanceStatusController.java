package com.spring_boot_template.presentation.controller.project.task;

import com.spring_boot_template.application.project.task.AdvanceStatusUseCase;
import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.project.task.response.AdvanceStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class AdvanceStatusController {
    private final AdvanceStatusUseCase advanceStatusUseCase;

    @PutMapping(value = "/project/{projectIdRequest}/task/{taskIdRequest}/status")
    @ResponseBody
    @Operation(
            tags = {"task"},
            summary = "タスクステータスを進める",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                AdvanceStatusResponse.class))),
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
    public ResponseEntity<?> execute(
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
        return ResponseEntity.ok(advanceStatusUseCase.execute(projectIdRequest, taskIdRequest));
    }
}

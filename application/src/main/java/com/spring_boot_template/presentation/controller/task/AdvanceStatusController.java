package com.spring_boot_template.presentation.controller.task;

import com.spring_boot_template.application.task.AdvanceStatusUseCase;
import com.spring_boot_template.domain.exception.DomainKnowledgeException;
import com.spring_boot_template.domain.exception.DomainNotFoundException;
import com.spring_boot_template.domain.exception.RequestInvalidException;
import com.spring_boot_template.presentation.controller.project.request.ProjectIdRequest;
import com.spring_boot_template.presentation.controller.task.request.TaskIdRequest;
import com.spring_boot_template.presentation.validator.ValidUlid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class AdvanceStatusController {
    private final AdvanceStatusUseCase advanceStatusUseCase;

    @PutMapping(value = "/project/{projectIdRequest}/task/{taskIdRequest}/status")
    @Operation(
            tags = {"task"},
            summary = "ステータスを進める",
            responses = {
                @ApiResponse(responseCode = "204", description = "No Content"),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema = @Schema(oneOf = {RequestInvalidException.class}))),
                @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content =
                                @Content(
                                        schema = @Schema(oneOf = {DomainNotFoundException.class}))),
                @ApiResponse(
                        responseCode = "422",
                        description = "Unprocessable Entity",
                        content =
                                @Content(
                                        schema = @Schema(oneOf = {DomainKnowledgeException.class})))
            })
    private ResponseEntity<?> execute(
            @PathVariable @ValidUlid final ProjectIdRequest projectIdRequest,
            @PathVariable @ValidUlid final TaskIdRequest taskIdRequest) {
        advanceStatusUseCase.execute(projectIdRequest, taskIdRequest);
        return ResponseEntity.noContent().build();
    }
}

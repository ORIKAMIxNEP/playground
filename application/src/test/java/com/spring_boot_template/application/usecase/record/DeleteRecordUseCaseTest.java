package com.spring_boot_template.application.usecase.record;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import com.spring_boot_template.application.usecase.record.impl.DeleteRecordUseCaseImpl;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.controller.record.request.DeleteRecordRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public final class DeleteRecordUseCaseTest {
    @Mock private ExistsRecordUseCase existsRecordUseCaseMock;
    @Mock private RecordRdbRepository recordRdbRepositoryMock;

    @InjectMocks private DeleteRecordUseCaseImpl deleteRecordUseCaseImpl;

    @Test
    public void executeTest() {
        doNothing()
                .when(existsRecordUseCaseMock)
                .execute(new RecordIdValue("0123456789ABCDEFGHJKMNPQRS"));
        doThrow(ValidationException.class)
                .when(existsRecordUseCaseMock)
                .execute(new RecordIdValue("00000000000000000000000000"));
        doNothing().when(recordRdbRepositoryMock).deleteRecord(any());

        assertThatThrownBy(
                        () ->
                                deleteRecordUseCaseImpl.execute(
                                        new DeleteRecordRequest("0123456789ABCDEFGHJKMNPQRS")))
                .doesNotThrowAnyException();
        assertThatThrownBy(
                        () ->
                                deleteRecordUseCaseImpl.execute(
                                        new DeleteRecordRequest("00000000000000000000000000")))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Record Not Found");
    }
}

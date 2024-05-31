package com.spring_boot_template.application.usecase.record;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

import com.spring_boot_template.application.usecase.record.impl.FetchRecordUseCaseImpl;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.domain.model.record.Column1Value;
import com.spring_boot_template.domain.model.record.Column2Value;
import com.spring_boot_template.domain.model.record.RecordEntity;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.controller.record.response.FetchRecordResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public final class FetchRecordUseCaseTest {
    @Mock private RecordRdbRepository recordRdbRepositoryMock;

    @InjectMocks private FetchRecordUseCaseImpl fetchRecordUseCaseImpl;

    @Test
    public void executeTest() {
        doReturn(
                        new RecordEntity(
                                new RecordIdValue("0123456789ABCDEFGHJKMNPQRS"),
                                new Column1Value((byte) 0),
                                new Column2Value("a")))
                .when(recordRdbRepositoryMock)
                .fetchRecord(new RecordIdValue("0123456789ABCDEFGHJKMNPQRS"));
        doReturn(null)
                .when(recordRdbRepositoryMock)
                .fetchRecord(new RecordIdValue("00000000000000000000000000"));

        assertThat(fetchRecordUseCaseImpl.execute("0123456789ABCDEFGHJKMNPQRS"))
                .isEqualTo(new FetchRecordResponse((byte) 0, "a"));
        assertThatThrownBy(
                        () -> {
                            fetchRecordUseCaseImpl.execute("00000000000000000000000000");
                        })
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("Record Not Found");
    }
}

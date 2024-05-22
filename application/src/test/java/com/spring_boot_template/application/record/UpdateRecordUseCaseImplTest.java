package com.spring_boot_template.application.record;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.spring_boot_template.application.record.impl.UpdateRecordUseCaseImpl;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.UpdateRecordRequest;
import com.spring_boot_template.presentation.record.response.UpdateRecordResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class UpdateRecordUseCaseImplTest {
  @Mock private RecordRdbRepository recordRdbRepositoryMock;

  @InjectMocks private UpdateRecordUseCaseImpl updateRecordUseCaseImpl;

  @Test
  public void executeTest() {
    doReturn(Record.builder().recordId(1).column1((byte) 0).column2("a").build())
        .when(recordRdbRepositoryMock)
        .fetchRecord(Record.builder().recordId(1).build());
    doReturn(null).when(recordRdbRepositoryMock).fetchRecord(Record.builder().recordId(2).build());

    assertThat(
            updateRecordUseCaseImpl.execute(
                UpdateRecordRequest.builder().recordId(1).column1((byte) 0).column2("a").build()))
        .isEqualTo(UpdateRecordResponse.builder().successful(true).build());
    assertThat(
            updateRecordUseCaseImpl.execute(
                UpdateRecordRequest.builder().recordId(2).column1((byte) 0).column2("a").build()))
        .isEqualTo(UpdateRecordResponse.builder().successful(false).build());
  }
}

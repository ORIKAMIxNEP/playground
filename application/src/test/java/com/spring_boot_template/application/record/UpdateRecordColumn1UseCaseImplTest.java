package com.spring_boot_template.application.record;

import static org.mockito.Mockito.doReturn;

import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.UpdateRecordColumn1Request;
import com.spring_boot_template.presentation.record.response.UpdateRecordColumn1Response;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class UpdateRecordColumn1UseCaseImplTest {
  @Mock private RecordRdbRepository recordRdbRepositoryMock;

  @InjectMocks private UpdateRecordColumn1UseCaseImpl updateRecordColumn1UseCaseImpl;

  @Test
  public void executeTest() {
    doReturn(Record.builder().recordId(1).column1((byte) 0).column2("a").build())
        .when(recordRdbRepositoryMock)
        .fetchRecord(Record.builder().recordId(1).build());
    doReturn(null).when(recordRdbRepositoryMock).fetchRecord(Record.builder().recordId(2).build());

    assertThat(
            updateRecordColumn1UseCaseImpl.execute(
                UpdateRecordColumn1Request.builder().recordId(1).column1((byte) 0).build()))
        .isEqualTo(UpdateRecordColumn1Response.builder().isSuccessful(true).build());
    assertThat(
            updateRecordColumn1UseCaseImpl.execute(
                UpdateRecordColumn1Request.builder().recordId(2).column1((byte) 0).build()))
        .isEqualTo(UpdateRecordColumn1Response.builder().isSuccessful(false).build());
  }
}

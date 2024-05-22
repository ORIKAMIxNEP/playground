package com.spring_boot_template.application.record;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.spring_boot_template.application.record.impl.DeleteRecordUseCaseImpl;
import com.spring_boot_template.domain.record.Record;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.DeleteRecordRequest;
import com.spring_boot_template.presentation.record.response.DeleteRecordResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class DeleteRecordUseCaseImplTest {
  @Mock private RecordRdbRepository recordRdbRepositoryMock;

  @InjectMocks private DeleteRecordUseCaseImpl deleteRecordUseCaseImpl;

  @Test
  public void executeTest() {
    doReturn(Record.builder().recordId(1).column1((byte) 0).column2("a").build())
        .when(recordRdbRepositoryMock)
        .fetchRecord(Record.builder().recordId(1).build());
    doReturn(null).when(recordRdbRepositoryMock).fetchRecord(Record.builder().recordId(2).build());

    assertThat(deleteRecordUseCaseImpl.execute(DeleteRecordRequest.builder().recordId(1).build()))
        .isEqualTo(DeleteRecordResponse.builder().successful(true).build());
    assertThat(deleteRecordUseCaseImpl.execute(DeleteRecordRequest.builder().recordId(2).build()))
        .isEqualTo(DeleteRecordResponse.builder().successful(false).build());
  }
}

package com.spring_boot_template.application.record;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import com.spring_boot_template.application.record.impl.AddRecordUseCaseImpl;
import com.spring_boot_template.infrastructure.record.RecordRdbRepository;
import com.spring_boot_template.presentation.record.request.AddRecordRequest;
import com.spring_boot_template.presentation.record.response.AddRecordResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class AddRecordUseCaseImplTest {
  @Mock private RecordRdbRepository recordRdbRepositoryMock;

  @InjectMocks private AddRecordUseCaseImpl addRecordUseCaseImpl;

  // レコード追加
  @Test
  public void executeTest() {
    doNothing().when(recordRdbRepositoryMock).addRecord(any());

    assertThat(
            addRecordUseCaseImpl.execute(
                AddRecordRequest.builder().column1((byte) 0).column2("a").build()))
        .isEqualTo(AddRecordResponse.builder().successful(true).build());
  }
}

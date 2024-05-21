package com.spring_boot_template.presentation.controller;

import com.spring_boot_template.application.record.impl.RecordUseCaseImpl;
import com.spring_boot_template.presentation.record.RecordController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class RecordControllerTest {
  @Mock private final RecordUseCaseImpl recordUseCaseImplMock;

  @InjectMocks private final RecordController recordController;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Value("${csrf.token}")
  private String csrfToken;

  //    @Test
  //    public void addTest01() {
  //        assertThat(recordController.add(1,"record2")).isEqualTo(new AddRecordOutput(true));
  //    }
  //
  //    @Test
  //    public void fetchTest01() {
  //        when(recordUseCaseMock.fetch().thenReturn(new FetchRecordOutput(1,"record2")));
  //        assertThat(recordController.fetch()).isEqualTo(new FetchRecordOutput(1,"record2"));
  //    }

  //  @Test
  //  public void fetchTest() {
  //    when(recordUseCaseImplMock.fetch().thenReturn(new FetchRecordOutput(0, "a")));
  //    assertThat(recordController.fetch()).isEqualTo(new FetchRecordOutput(0, "a"));
  //  }
  //
  //  @Test
  //  public void updateTest() {
  //    assertThat(recordController.update(0, "a")).isEqualTo(new UpdateRecordOutput(true));
  //  }
  //
  //  @Test
  //  public void updateColumn1Test() {
  //    assertThat(recordController.updateColumn1(1)).isEqualTo(new UpdateColumn1Output(true));
  //  }
  //
  //  @Test
  //  public void deleteTest() {
  //    assertThat(recordController.delete()).isEqualTo(new DeleteRecordOutput(true));
  //  }
}

package jp.spring_boot_template.application.usecase;

import jp.spring_boot_template.application.dto.record.AddRecordInput;
import jp.spring_boot_template.application.dto.record.AddRecordOutput;
import jp.spring_boot_template.application.dto.record.DeleteRecordInput;
import jp.spring_boot_template.application.dto.record.DeleteRecordOutput;
import jp.spring_boot_template.application.dto.record.FetchRecordOutput;
import jp.spring_boot_template.application.dto.record.UpdateRecordColumn1Input;
import jp.spring_boot_template.application.dto.record.UpdateRecordColumn1Output;
import jp.spring_boot_template.application.dto.record.UpdateRecordInput;
import jp.spring_boot_template.application.dto.record.UpdateRecordOutput;
import jp.spring_boot_template.application.record.impl.RecordUseCaseImpl;
import jp.spring_boot_template.domain.record.Record;
import jp.spring_boot_template.infrastructure.record.RecordRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class RecordUseCaseImplTest {
  @Mock private final RecordRepositoryImpl recordRepositoryImplMock;

  @InjectMocks private final RecordUseCaseImpl recordUseCaseImpl;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void addRecordTest() {
    assertThat(
            recordUseCaseImpl.addRecord(
                AddRecordInput.builder().column1((byte) 0).column2("a").build()))
        .isEqualTo(AddRecordOutput.builder().success(true).build());
  }

  @Test
  public void fetchRecordTest() {
    when(recordRepositoryImplMock.fetchRecord(1))
        .thenReturn(Record.builder().recordId(1).column1((byte) 0).column2("a").build());
    when(recordRepositoryImplMock.fetchRecord(2)).thenReturn(null);

    assertThat(recordUseCaseImpl.fetchRecord())
        .isEqualTo(
            FetchRecordOutput.builder().success(true).column1((byte) 0).column2("a").build());
    assertThat(recordUseCaseImpl.fetchRecord())
        .isEqualTo(FetchRecordOutput.builder().success(false).column1(null).column2(null).build());
  }

  @Test
  public void updateRecordTest() {
    when(recordRepositoryImplMock.fetchRecord(1))
        .thenReturn(Record.builder().recordId(1).column1((byte) 0).column2("a").build());
    when(recordRepositoryImplMock.fetchRecord(2)).thenReturn(null);

    assertThat(
            recordUseCaseImpl.updateRecord(
                UpdateRecordInput.builder().recordId(1).column1((byte) 0).column2("a").build()))
        .isEqualTo(UpdateRecordOutput.builder().success(true).build());
    assertThat(
            recordUseCaseImpl.updateRecord(
                UpdateRecordInput.builder().recordId(2).column1((byte) 0).column2("a").build()))
        .isEqualTo(UpdateRecordOutput.builder().success(false).build());
  }

  @Test
  public void updateRecordColumn1Test() {
    when(recordRepositoryImplMock.fetchRecord(1))
        .thenReturn(Record.builder().recordId(1).column1((byte) 0).column2("a").build());
    when(recordRepositoryImplMock.fetchRecord(2)).thenReturn(null);

    assertThat(
            recordUseCaseImpl.updateRecordColumn1(
                UpdateRecordColumn1Input.builder().recordId(1).column1((byte) 0).build()))
        .isEqualTo(UpdateRecordColumn1Output.builder().success(true).build());
    assertThat(
            recordUseCaseImpl.updateRecordColumn1(
                UpdateRecordColumn1Input.builder().recordId(2).column1((byte) 0).build()))
        .isEqualTo(UpdateRecordColumn1Output.builder().success(false).build());
  }

  @Test
  public void deleteRecordTest() {
    when(recordRepositoryImplMock.fetchRecord(1))
        .thenReturn(Record.builder().recordId(1).column1((byte) 0).column2("a").build());
    when(recordRepositoryImplMock.fetchRecord(2)).thenReturn(null);

    assertThat(recordUseCaseImpl.deleteRecord(DeleteRecordInput.builder().recordId(1).build()))
        .isEqualTo(DeleteRecordOutput.builder().success(true).build());
    assertThat(recordUseCaseImpl.deleteRecord(DeleteRecordInput.builder().recordId(2).build()))
        .isEqualTo(DeleteRecordOutput.builder().success(false).build());
  }
}

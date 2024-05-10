package jp.spring_boot_template.application.service;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.spring_boot_template.infrastructure.repository.RecordsRepository;
import jp.spring_boot_template.domain.entity.Records;

import org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class RecordUseCaseTest {
    @Mock
    RecordsRepository recordsRepositoryMock;

    @InjectMocks
    private RecordUseCase recordUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addTest01() {
        assertThat(recordUseCase.add(1,"record2")).isEqualTo(new AddResponse(true));
    }

    @Test
    public void fetchTest01() {
        when(recordsRepositoryMock.fetch().thenReturn(new Records(1,1,"record2")));
        assertThat(recordUseCase.fetch()).isEqualTo(new FetchResponse(1,"record2"));
    }

    @Test
    public void updateTest01() {
        assertThat(recordUseCase.update(1,"record2")).isEqualTo(new UpdateResponse(true));
    }

    @Test
    public void updateRecord1Test01() {
        assertThat(recordUseCase.updateRecord1(1)).isEqualTo(new UpdateRecord1Response(true));
    }

    @Test
    public void deleteTest01() {
        assertThat(recordUseCase.delete()).isEqualTo(new DeleteResponse(true));
    }
}

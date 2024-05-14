package jp.spring_boot_template.presentation.controller;
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
public class RecordControllerTest {
    @Mock
    private final RecordUseCase recordUseCaseMock;

    @InjectMocks
    private final RecordController recordController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Value("${csrf.token}")
    private final String csrfToken;

    @Test
    public void addTest01() {
        assertThat(recordController.add(1,"record2")).isEqualTo(new AddResponse(true));
    }

    @Test
    public void fetchTest01() {
        when(recordUseCaseMock.fetch().thenReturn(new FetchResponse(1,"record2")));
        assertThat(recordController.fetch()).isEqualTo(new FetchResponse(1,"record2"));
    }

    @Test
    public void updateTest01() {
        assertThat(recordController.update(1,"record2")).isEqualTo(new UpdateResponse(true));
    }

    @Test
    public void updateRecord1Test01() {
        assertThat(recordController.updateRecord1(1)).isEqualTo(new UpdateRecord1Response(true));
    }

    @Test
    public void deleteTest01() {
        assertThat(recordController.delete()).isEqualTo(new DeleteResponse(true));
    }
}

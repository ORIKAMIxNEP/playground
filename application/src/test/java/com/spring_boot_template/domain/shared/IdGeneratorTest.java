package com.spring_boot_template.domain.shared;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring_boot_template.domain.module.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
final class IdGeneratorTest {
    private final IdGenerator idGenerator;

    @Test
    public void testGenerateId() {
        final String id = idGenerator.generateId();
        final int actualIdLength = id.length();
        final int expectedIdLength = 26;

        assertThat(actualIdLength).isEqualTo(expectedIdLength);
    }
}

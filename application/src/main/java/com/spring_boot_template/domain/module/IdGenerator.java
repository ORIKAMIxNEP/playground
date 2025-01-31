package com.spring_boot_template.domain.module;

import de.huxhorn.sulky.ulid.ULID;
import org.springframework.stereotype.Component;

@Component
public final class IdGenerator {
    private final ULID ulid = new ULID();

    public String generateId() {
        return ulid.nextValue().toString();
    }
}

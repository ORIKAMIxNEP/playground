package com.spring_boot_template.domain.shared;

import java.util.UUID;

public class IdGenerator {
    public static String generate() {
        final UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

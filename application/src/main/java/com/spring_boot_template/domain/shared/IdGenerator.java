package com.spring_boot_template.domain.shared;

import de.huxhorn.sulky.ulid.ULID;

public final class IdGenerator {
    private static final ULID ULID_INSTANCE = new ULID();

    public static String generateId() {
        return ULID_INSTANCE.nextValue().toString();
    }
}

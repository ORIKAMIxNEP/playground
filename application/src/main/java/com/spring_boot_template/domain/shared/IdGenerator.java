package com.spring_boot_template.domain.shared;

import de.huxhorn.sulky.ulid.ULID;

public class IdGenerator {
    public static String generate() {
        final ULID ulid = new ULID();
        final ULID.Value ulidValue = ulid.nextValue();

        return ulidValue.toString();
    }
}

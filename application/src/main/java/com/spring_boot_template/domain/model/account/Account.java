package com.spring_boot_template.domain.model.account;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.account.value.AccountName;
import com.spring_boot_template.domain.model.account.value.Password;
import com.spring_boot_template.domain.shared.IdGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Account {
    private final AccountId id;
    private AccountName name;
    private Password password;

    public static Account create(final AccountName name, final Password password) {
        final AccountId id = new AccountId(IdGenerator.generate());

        return new Account(id, name, password);
    }

    public static Account reconstruct(
            final AccountId id, final AccountName name, final Password password) {
        return new Account(id, name, password);
    }
}

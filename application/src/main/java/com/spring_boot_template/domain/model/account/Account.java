package com.spring_boot_template.domain.model.account;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.account.value.AccountName;
import com.spring_boot_template.domain.model.account.value.Password;
import com.spring_boot_template.domain.module.IdGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Account {
    private final AccountId accountId;
    private AccountName accountName;
    private Password password;

    public static Account createAccount(
            final IdGenerator idGenerator, final AccountName accountName, final Password password) {
        final AccountId accountId = new AccountId(idGenerator.generateId());

        return new Account(accountId, accountName, password);
    }

    public static Account reconstructAccount(
            final AccountId accountId, final AccountName accountName, final Password password) {
        return new Account(accountId, accountName, password);
    }
}

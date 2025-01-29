package com.spring_boot_template.domain.model.account;

import com.spring_boot_template.domain.model.account.value.AccountId;

public interface AccountRepository {
    void saveAccount(final Account account);

    Account findAccountByAccountId(final AccountId accountId);

    void deleteAccount(final AccountId accountId);
}

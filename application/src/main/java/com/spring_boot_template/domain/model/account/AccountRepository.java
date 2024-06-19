package com.spring_boot_template.domain.model.account;

import com.spring_boot_template.domain.model.account.value.AccountId;

public interface AccountRepository {
    void saveAccount(final Account account);

    void deleteAccount(final AccountId accountId);

    Account findAccountByAccountId(final AccountId accountId);
}

package com.spring_boot_template.domain.model.account;

import com.spring_boot_template.domain.model.account.value.AccountId;

public interface AccountRepository {
    void save(final Account account);

    void delete(final AccountId id);

    Account findById(final AccountId id);
}

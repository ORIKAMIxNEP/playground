package com.spring_boot_template.domain.model.account.factory;

import com.spring_boot_template.domain.model.account.Account;
import com.spring_boot_template.domain.model.account.value.AccountName;
import com.spring_boot_template.domain.model.account.value.Password;

public interface AccountFactory {
    Account create(final AccountName name, final Password password);
}

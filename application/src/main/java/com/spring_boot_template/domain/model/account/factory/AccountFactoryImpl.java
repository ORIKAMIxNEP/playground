package com.spring_boot_template.domain.model.account.factory;

import com.spring_boot_template.domain.model.account.Account;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.account.value.AccountName;
import com.spring_boot_template.domain.model.account.value.Password;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.shared.IdGenerator;
import java.util.LinkedHashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class AccountFactoryImpl implements AccountFactory {
    @Override
    public Account create(final AccountName name, final Password password) {
        final AccountId id = new AccountId(IdGenerator.generate());
        final LinkedHashSet<ProjectId> participatingProjectIds = new LinkedHashSet<>();

        return new Account(id, name, password, participatingProjectIds);
    }
}

package com.spring_boot_template.domain.model.account;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.account.value.AccountName;
import com.spring_boot_template.domain.model.account.value.Password;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.shared.IdGenerator;
import java.util.HashSet;
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
    private final HashSet<ProjectId> participatingProjectIds;

    public static Account create(final AccountName name, final Password password) {
        final AccountId id = new AccountId(IdGenerator.generate());
        final HashSet<ProjectId> participatingProjectIds = new HashSet<>();

        return new Account(id, name, password, participatingProjectIds);
    }

    public static Account reconstruct(
            final AccountId id,
            final AccountName name,
            final Password password,
            final HashSet<ProjectId> participatingProjectIds) {
        return new Account(id, name, password, participatingProjectIds);
    }
}

package com.spring_boot_template.domain.model.account;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.account.value.AccountName;
import com.spring_boot_template.domain.model.account.value.Password;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import java.util.LinkedHashSet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public final class Account {
    private final AccountId id;
    private AccountName name;
    private Password password;
    private final LinkedHashSet<ProjectId> participatingProjectIds;
}

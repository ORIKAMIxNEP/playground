package com.spring_boot_template.application.project;

import com.spring_boot_template.application.project.query.ProjectQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import java.util.List;

public interface FetchProjectsUseCase {
    List<ProjectQueryDto> execute(final AccountId participatingAccountId);
}

package com.spring_boot_template.application.project.query;

import com.spring_boot_template.application.project.query.dto.ProjectQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import java.util.ArrayList;

public interface ProjectQueryService {
    ArrayList<ProjectQueryDto> findProjectsByAccountId(final AccountId participatingAccountId);

    ArrayList<ProjectId> findProjectIdsByAccountId(final AccountId participatingAccountId);
}

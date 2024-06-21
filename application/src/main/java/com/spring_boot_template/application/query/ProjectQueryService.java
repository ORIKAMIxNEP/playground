package com.spring_boot_template.application.query;

import com.spring_boot_template.application.query.dto.ProjectQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import java.util.ArrayList;

public interface ProjectQueryService {
    ArrayList<ProjectQueryDto> fetchProjectsByAccountId(final AccountId accountId);

    ArrayList<ProjectId> fetchProjectIdsByAccountId(final AccountId accountId);
}

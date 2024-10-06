package com.spring_boot_template.application.project.query;

import com.spring_boot_template.domain.model.account.value.AccountId;
import java.util.List;

public interface ProjectQueryService {
    List<ProjectQueryDto> findProjectsByParticipatingAccountId(
            final AccountId participatingAccountId);
}

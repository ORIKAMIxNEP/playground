package com.spring_boot_template.application.project.query;

import com.spring_boot_template.domain.model.account.value.AccountId;
import java.util.ArrayList;

public interface ProjectQueryService {
    ArrayList<ProjectQueryDto> findProjectsByAccountId(final AccountId participatingAccountId);
}

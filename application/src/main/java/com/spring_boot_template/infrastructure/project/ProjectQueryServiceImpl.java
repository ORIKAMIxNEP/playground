package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.application.query.ProjectQueryService;
import com.spring_boot_template.application.query.dto.ProjectQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectMapper projectMapper;

    @Override
    public ArrayList<ProjectQueryDto> fetchProjectsByAccountId(AccountId accountId) {
        return null;
    }

    @Override
    public ArrayList<ProjectId> fetchProjectIdsByAccountId(AccountId accountId) {
        return null;
    }
}

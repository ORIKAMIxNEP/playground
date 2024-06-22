package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.application.project.query.dto.ProjectQueryDto;
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
    public ArrayList<ProjectQueryDto> findProjectsByAccountId(
            final AccountId participatingAccountId) {
        return projectMapper.selectProjectsByAccountId(participatingAccountId);
    }

    @Override
    public ArrayList<ProjectId> findProjectIdsByAccountId(final AccountId participatingAccountId) {
        return projectMapper.selectProjectIdsByAccountId(participatingAccountId);
    }
}

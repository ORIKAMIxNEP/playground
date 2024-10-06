package com.spring_boot_template.infrastructure.project.query;

import com.spring_boot_template.application.project.query.ProjectQueryDto;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.model.account.value.AccountId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectQueryMapper projectQueryMapper;

    @Override
    public List<ProjectQueryDto> findProjectsByParticipatingAccountId(
            final AccountId participatingAccountId) {
        return projectQueryMapper.selectProjectsByParticipatingAccountId(participatingAccountId);
    }
}

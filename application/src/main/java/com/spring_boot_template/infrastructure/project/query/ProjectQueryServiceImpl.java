package com.spring_boot_template.infrastructure.project.query;

import com.spring_boot_template.application.project.query.ProjectQueryDto;
import com.spring_boot_template.application.project.query.ProjectQueryService;
import com.spring_boot_template.domain.model.account.value.AccountId;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectQueryMapper projectQueryMapper;

    @Override
    public ArrayList<ProjectQueryDto> findProjectsByAccountId(
            final AccountId participatingAccountId) {
        final Optional<ArrayList<ProjectQueryDto>> optionalProjectQueryDtos =
                Optional.ofNullable(
                        projectQueryMapper.selectProjectsByAccountId(participatingAccountId));

        return optionalProjectQueryDtos.orElseGet(ArrayList::new);
    }
}

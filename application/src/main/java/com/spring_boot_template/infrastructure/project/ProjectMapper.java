package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.infrastructure.project.dto.ProjectDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
interface ProjectMapper {
    void upsertProject(final Project project);

    void insertParticipatingAccount(
            final ProjectId projectId, final AccountId participatingAccountId);

    ProjectDto selectProjectByProjectId(final ProjectId projectId);

    void deleteProject(final ProjectId projectId);

    void deleteParticipatingAccounts(final ProjectId projectId);
}

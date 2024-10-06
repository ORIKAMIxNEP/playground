package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface ProjectMapper {
    void upsertProject(final Project project);

    void insertParticipatingAccount(
            @Param("projectId") final ProjectId projectId,
            @Param("participatingAccountId") final AccountId participatingAccountId);

    ProjectDto selectProjectByProjectId(@Param("projectId") final ProjectId projectId);

    void deleteProject(@Param("projectId") final ProjectId projectId);

    void deleteParticipatingAccounts(@Param("projectId") final ProjectId projectId);
}

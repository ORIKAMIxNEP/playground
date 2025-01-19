package com.spring_boot_template.domain.model.project;

import com.spring_boot_template.domain.model.project.value.ProjectId;

public interface ProjectRepository {
    void saveProject(final Project project);

    Project findProjectByProjectId(final ProjectId projectId);

    void deleteProject(final ProjectId projectId);
}

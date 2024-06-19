package com.spring_boot_template.domain.model.project;

import com.spring_boot_template.domain.model.project.value.ProjectId;

public interface ProjectRepository {
    void saveProject(final Project project);

    void deleteProject(final ProjectId projectId);

    Project findProjectByProjectId(final ProjectId projectId);
}

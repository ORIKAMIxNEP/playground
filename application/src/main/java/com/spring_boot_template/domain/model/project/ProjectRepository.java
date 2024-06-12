package com.spring_boot_template.domain.model.project;

import com.spring_boot_template.domain.model.project.value.ProjectId;

public interface ProjectRepository {
    void save(final Project project);

    void delete(final ProjectId id);

    Project findById(final ProjectId id);
}

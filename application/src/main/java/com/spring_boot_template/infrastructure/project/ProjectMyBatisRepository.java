package com.spring_boot_template.infrastructure.project;

import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.ProjectRepository;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectMyBatisRepository implements ProjectRepository {
    private final ProjectMapper projectMapper;

    @Override
    public void save(final Project project) {}

    @Override
    public Project findById(final ProjectId projectId) {
        return null;
    }

    @Override
    public void delete(final ProjectId projectId) {}
}

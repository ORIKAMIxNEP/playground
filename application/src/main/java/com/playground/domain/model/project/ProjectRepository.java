package com.playground.domain.model.project;

import com.playground.domain.model.project.value.ProjectId;

public interface ProjectRepository {
  void saveProject(final Project project);

  Project findProjectByProjectId(final ProjectId projectId);

  void deleteProject(final ProjectId projectId);
}

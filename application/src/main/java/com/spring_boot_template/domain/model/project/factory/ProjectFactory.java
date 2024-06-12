package com.spring_boot_template.domain.model.project.factory;

import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.value.ProjectName;

public interface ProjectFactory {
    Project create(final ProjectName name);
}

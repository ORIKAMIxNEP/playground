package com.spring_boot_template.domain.model.project.factory;

import com.spring_boot_template.domain.model.account.value.AccountId;
import com.spring_boot_template.domain.model.project.Project;
import com.spring_boot_template.domain.model.project.value.ProjectId;
import com.spring_boot_template.domain.model.project.value.ProjectName;
import com.spring_boot_template.domain.model.task.Task;
import com.spring_boot_template.domain.shared.IdGenerator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class ProjectFactoryImpl implements ProjectFactory {
    @Override
    public Project create(final ProjectName name) {
        final ProjectId id = new ProjectId(IdGenerator.generate());
        final HashSet<AccountId> participatedAccountIds = new HashSet<>();
        final LinkedHashSet<Task> tasks = new LinkedHashSet<>();

        return new Project(id, name, participatedAccountIds, tasks);
    }
}

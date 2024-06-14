package com.spring_boot_template.infrastructure.project.request;

import java.util.List;

public record ProjectDto (
  ProjectId id,
  ProjectName name,
  List<AccountId> participatedAccountIds
) {}

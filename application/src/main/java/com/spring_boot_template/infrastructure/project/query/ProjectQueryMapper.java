package com.spring_boot_template.infrastructure.project.query;

import com.spring_boot_template.application.project.query.ProjectQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface ProjectQueryMapper {
    List<ProjectQueryDto> selectProjectsByParticipatingAccountId(
            @Param("participatingAccountId") final AccountId participatingAccountId);
}

package com.spring_boot_template.infrastructure.project.query;

import com.spring_boot_template.application.project.query.ProjectQueryDto;
import com.spring_boot_template.domain.model.account.value.AccountId;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
interface ProjectQueryMapper {
    ArrayList<ProjectQueryDto> selectProjectsByAccountId(
            @Param("participatingAccountId") final AccountId participatingAccountId);
}

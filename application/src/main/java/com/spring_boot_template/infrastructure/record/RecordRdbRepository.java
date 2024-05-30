package com.spring_boot_template.infrastructure.record;

import com.spring_boot_template.domain.model.record.RecordEntity;
import com.spring_boot_template.domain.model.record.RecordIdValue;
import com.spring_boot_template.domain.model.record.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordRdbRepository implements RecordRepository {
    private final RecordMapper recordMapper;

    public void addRecord(final RecordEntity recordEntity) {
        recordMapper.addRecord(recordEntity);
    }

    public RecordEntity fetchRecord(final RecordIdValue recordIdValue) {
        return recordMapper.fetchRecord(recordIdValue);
    }

    public void updateRecord(final RecordEntity recordEntity) {
        recordMapper.updateRecord(recordEntity);
    }

    public void deleteRecord(final RecordIdValue recordIdValue) {
        recordMapper.deleteRecord(recordIdValue);
    }
}

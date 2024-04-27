package jp.sandbox.application.service;

import jp.sandbox.domain.dto.record.AddRequest;
import jp.sandbox.domain.dto.record.AddResponse;
import jp.sandbox.domain.dto.record.DeleteResponse;
import jp.sandbox.domain.dto.record.FetchResponse;
import jp.sandbox.domain.dto.record.UpdateRecord1Request;
import jp.sandbox.domain.dto.record.UpdateRecord1Response;
import jp.sandbox.domain.dto.record.UpdateRequest;
import jp.sandbox.domain.dto.record.UpdateResponse;
import jp.sandbox.domain.entity.Records;
import jp.sandbox.infrastructure.repository.RecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordService {
  private final RecordsRepository recordsRepository;

  // レコード追加
  public AddResponse add(final AddRequest addRequest) {
    final String record1 = addRequest.record1();
    final String record2 = addRequest.record2();

    recordsRepository.add(record1, record2);

    return new AddResponse(true);
  }

  // レコード取得
  public FetchResponse fetch() {
    final Records records = recordsRepository.fetch(0);

    return new FetchResponse(records.getRecord1(), records.getRecord2());
  }

  // レコード更新
  public UpdateResponse update(final UpdateRequest updateRequest) {
    final String record1 = updateRequest.record1();
    final String record2 = updateRequest.record2();

    recordsRepository.update(0, record1, record2);

    return new UpdateResponse(true);
  }

  // レコード1更新
  public UpdateRecord1Response updateRecord1(final UpdateRecord1Request updateRecord1Request) {
    recordsRepository.updateRecord1(0, updateRecord1Request.record1());

    return new UpdateRecord1Response(true);
  }

  // レコード削除
  public DeleteResponse delete() {
    recordsRepository.delete(0);

    return new DeleteResponse(true);
  }
}

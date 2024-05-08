package jp.spring_boot_template.application.usecase;

import jp.spring_boot_template.application.dto.record.AddRequest;
import jp.spring_boot_template.application.dto.record.AddResponse;
import jp.spring_boot_template.application.dto.record.DeleteResponse;
import jp.spring_boot_template.application.dto.record.FetchResponse;
import jp.spring_boot_template.application.dto.record.UpdateRecord1Request;
import jp.spring_boot_template.application.dto.record.UpdateRecord1Response;
import jp.spring_boot_template.application.dto.record.UpdateRequest;
import jp.spring_boot_template.application.dto.record.UpdateResponse;
import jp.spring_boot_template.domain.entity.Records;
import jp.spring_boot_template.infrastructure.repository.RecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordUseCase {
  private final RecordsRepository recordsRepository;

  // レコード追加
  public AddResponse add(final AddRequest addRequest) {
    final short record1 = addRequest.record1();
    final String record2 = addRequest.record2();

    recordsRepository.add(record1, record2);

    return new AddResponse(true);
  }

  // レコード取得
  public FetchResponse fetch() {
    final Records records = recordsRepository.fetch(1);

    return new FetchResponse(records.getRecord1(), records.getRecord2());
  }

  // レコード更新
  public UpdateResponse update(final UpdateRequest updateRequest) {
    final short record1 = updateRequest.record1();
    final String record2 = updateRequest.record2();

    recordsRepository.update(1, record1, record2);

    return new UpdateResponse(true);
  }

  // レコード1更新
  public UpdateRecord1Response updateRecord1(final UpdateRecord1Request updateRecord1Request) {
    recordsRepository.updateRecord1(1, updateRecord1Request.record1());

    return new UpdateRecord1Response(true);
  }

  // レコード削除
  public DeleteResponse delete() {
    recordsRepository.delete(1);

    return new DeleteResponse(true);
  }
}

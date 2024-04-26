package jp.sandbox.application.service;

import jakarta.servlet.http.HttpServletRequest;
import jp.sandbox.domain.dto.record.AddRequest;
import jp.sandbox.domain.dto.record.AddResponse;
import jp.sandbox.domain.dto.record.FetchResponse;
import jp.sandbox.domain.dto.record.UpdatePasswordRequest;
import jp.sandbox.domain.dto.record.UpdatePasswordResponse;
import jp.sandbox.domain.dto.record.UpdateRecordRequest;
import jp.sandbox.domain.dto.record.UpdateRecordResponse;
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

    // レコード1またはレコード2が空である場合
    if (record1.isEmpty() || record2.isEmpty()) {
      return new AddResponse(false);
    }

    recordsRepository.add(record1, record2);

    return new AddResponse(true);
  }

  // レコード取得
  public FetchResponse fetch() {
    final Records records=recordsRepository.fetch(0);
    return new FetchResponse(records.getRecord1(),records.getRecord2());
  }

  // レコード更新
  public UpdateRecordResponse updateRecord(
      final UpdateRecordRequest updateRecordRequest) {
    final String record1 = addRequest.record1();
    final String record2 = addRequest.record2();

    // レコード1またはレコード2が空である場合
    if (record1.isEmpty() || record2.isEmpty()) {
      return new Response(false);
    }

    recordsRepository.updateRecord(recordIdFetcher.fetch(httpServletRequest), formattedRecord);

    return new UpdateRecordResponse(true);
  }

  // レコードパスワード更新
  public UpdatePasswordResponse updatePassword(
      final UpdatePasswordRequest updatePasswordRequest,
      final HttpServletRequest httpServletRequest) {
    final String formattedPassword = stringFormatter.format(updatePasswordRequest.record2());

    // レコードパスワードが空である場合
    if (formattedPassword.isEmpty()) {
      return new UpdatePasswordResponse(false);
    }

    recordsRepository.updatePassword(
        recordIdFetcher.fetch(httpServletRequest), record2Encoder.hash(formattedPassword));

    return new UpdatePasswordResponse(true);
  }
}

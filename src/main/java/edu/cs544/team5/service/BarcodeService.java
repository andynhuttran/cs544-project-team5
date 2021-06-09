package edu.cs544.team5.service;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.dto.BarcodeRecordCreationDto;
import edu.cs544.team5.dto.BarcodeRecordReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BarcodeService {
    BarcodeRecordReadDto create(BarcodeRecordCreationDto barcodeRecord);

    Page<BarcodeRecord> fetchAll(Pageable request);

    Page<BarcodeRecord> getBarcodeByStudentAndClassSession(int courseId, int studentId, Pageable pageable);
}

package edu.cs544.team5.services;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.domain.ClassSession;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.BarcodeRecordCreationDto;
import edu.cs544.team5.repository.BarcodeRepository;
import edu.cs544.team5.service.BarcodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

class BarcodeRecordServiceImplTest {
    @Autowired
    private BarcodeService personService;

    @MockBean
    private BarcodeRepository barcodeRepository;

    @Test
    @DisplayName("create barcode")
    void createBarcode() {
        Student student = null; // stub from student repository
        ClassSession classSession = null; // stub from classSession repository
        BarcodeRecord barcodeRecord = new BarcodeRecord();
        barcodeRecord.setId(1);
        barcodeRecord.setStudent(student);
        barcodeRecord.setClassSession(classSession);
        barcodeRecord.setAttendance(LocalDateTime.MAX);
        when(barcodeRepository.save(barcodeRecord)).thenReturn(barcodeRecord);

        @Nested
        @DisplayName("should")
        class Should {
            @Test
            @DisplayName("create and return BarcodeRecord object")
            void createAndReturnBarcodeRecordObject() {
                BarcodeRecordCreationDto barcodeRecordCreationDto = new BarcodeRecordCreationDto();
                fail("Not Implemented");
            }
        }
    }
}

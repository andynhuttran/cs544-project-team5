package edu.cs544.team5.repository;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.domain.ClassSession;
import edu.cs544.team5.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
class BarcodeRepositoryTest {
    @Autowired
    private BarcodeRepository barcodeRepository;

    @Test
    @DisplayName("should create BarcodeRecord with student and class session")
    void shouldCreateBarcodeRecord() {
        Student student = null; // stub from student repository
        ClassSession classSession = null; // stub from classSession repository
        BarcodeRecord barcodeRecord = new BarcodeRecord();
        barcodeRecord.setId(1);
        barcodeRecord.setStudent(student);
        barcodeRecord.setClassSession(classSession);
        barcodeRecord.setAttendance(LocalDateTime.MAX);

        @Nested
        @DisplayName("should")
        class should {
            @Test
            @DisplayName("create and return BarcodeRecord object")
            void createAndReturnBarcodeRecordObject() {
                BarcodeRecord record = barcodeRepository.save(barcodeRecord);
                assertAll(
                        () -> assertEquals(barcodeRecord, record) // add more test
                );
            }
        }
    }
}

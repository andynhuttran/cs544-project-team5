package edu.cs544.team5.controller;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.domain.ClassSession;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.BarcodeRecordCreationDto;
import edu.cs544.team5.repository.ClassSessionRepository;
import edu.cs544.team5.repository.StudentRepository;
import edu.cs544.team5.services.BarcodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/record")
@RestController()
public class BarcodeController {
    private final BarcodeService barcodeService;
    private final StudentRepository studentRepository;
    private final ClassSessionRepository classSessionRepository;

    public BarcodeController(BarcodeService barcodeService, StudentRepository studentRepository, ClassSessionRepository classSessionRepository) {
        this.barcodeService = barcodeService;
        this.studentRepository = studentRepository;
        this.classSessionRepository = classSessionRepository;
    }

    /**
     * @param brDTO
     * @return created BarcodeRecord object as a JSON with 201 HTTP status
     */
    @PostMapping
    public ResponseEntity<BarcodeRecord> create(@Valid @RequestBody BarcodeRecordCreationDto brDTO) {
        ClassSession classSession = classSessionRepository.getById(brDTO.getClassSessionCreationDto().getId());
        Student student = studentRepository.getById(brDTO.getStudentCreationDto().getId());

        BarcodeRecord barcodeRecord = new BarcodeRecord();
        barcodeRecord.setAttendance(brDTO.getAttendance());
        barcodeRecord.setClassSession(classSession);
        barcodeRecord.setStudent(student);
        BarcodeRecord created = barcodeService.create(barcodeRecord);
        return ResponseEntity.created(URI.create("")).body(created);
    }
}

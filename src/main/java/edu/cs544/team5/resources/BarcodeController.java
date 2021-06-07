package edu.cs544.team5.resources;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.domain.ClassSession;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.BarcodeRecordCreationDto;
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

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    /**
     * @param brDTO
     * @return created BarcodeRecord object as a JSON with 201 HTTP status
     * @Todo replace the brDTO type to the relative DTO
     */
    @PostMapping
    public ResponseEntity<BarcodeRecord> create(@Valid @RequestBody BarcodeRecordCreationDto brDTO) {
        /**
         * @Todo
         * get student and class session from DB based on the request @brDTO
         * to verify requested student id and class session id are available.
         */
        ClassSession classSession = null;
        Student student = null;

        BarcodeRecord barcodeRecord = new BarcodeRecord();
        barcodeRecord.setAttendance(brDTO.getAttendance());
        barcodeRecord.setClassSession(classSession);
        barcodeRecord.setStudent(student);
        barcodeRecord.setAttendance(brDTO.getAttendance());
        BarcodeRecord created = barcodeService.create(barcodeRecord);
        return ResponseEntity.created(URI.create("")).body(created);
    }
}

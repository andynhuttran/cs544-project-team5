package edu.cs544.team5.controller;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.dto.BarcodeRecordCreationDto;
import edu.cs544.team5.dto.BarcodeRecordReadDto;
import edu.cs544.team5.dto.ClassSessionReadDto;
import edu.cs544.team5.dto.StudentReadDto;
import edu.cs544.team5.service.BarcodeService;
import edu.cs544.team5.service.ClassSessionService;
import edu.cs544.team5.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/record")
@RestController
@RequiredArgsConstructor
public class BarcodeController {
    private final BarcodeService barcodeService;
    private final StudentService studentService;
    private final ClassSessionService classSessionService;

    /**
     * @param brDTO request body
     * @return created BarcodeRecord object as a JSON with 201 HTTP status
     */
    @PostMapping
    public ResponseEntity<BarcodeRecordReadDto> create(@Valid @RequestBody BarcodeRecordCreationDto brDTO) {
        ClassSessionReadDto classSessionDTO = classSessionService.findById(brDTO.getClassSessionReadDto().getId());
        StudentReadDto studentDTO = studentService.findById(brDTO.getStudentReadDto().getId());

        BarcodeRecordCreationDto barcodeRecord = new BarcodeRecordCreationDto();
        barcodeRecord.setAttendance(brDTO.getAttendance());
        barcodeRecord.setClassSessionReadDto(classSessionDTO);
        barcodeRecord.setStudentReadDto(studentDTO);
        BarcodeRecordReadDto created = barcodeService.create(barcodeRecord);
        return ResponseEntity.created(URI.create("")).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<BarcodeRecord>> fetchAll(
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        Sort sortBy = Sort.by(order.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sort);
        Pageable request = PageRequest.of(page, pageSize, sortBy);
        return ResponseEntity.ok(barcodeService.fetchAll(request));
    }
}

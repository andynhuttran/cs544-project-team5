package edu.cs544.team5.controller;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.dto.*;
import edu.cs544.team5.service.BarcodeService;
import edu.cs544.team5.service.ClassSessionService;
import edu.cs544.team5.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1//record")
@RestController
@RequiredArgsConstructor
public class BarcodeController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private BarcodeService barcodeService;
    @Autowired
    private ClassSessionService classSessionService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param brDTO request body
     * @return created BarcodeRecord object as a JSON with 201 HTTP status
     */
    @PostMapping
    public ResponseEntity<BarcodeRecordReadDto> create(@Valid @RequestBody BarcodeRecordCreationDto brDTO) {
        ClassSessionReadDto classSessionDTO = classSessionService.findById(brDTO.getClassSessionReadDto().getId());
        StudentReadDto studentDTO = studentService.findByBarcode(brDTO.getStudentReadDto().getBarcode());

        BarcodeRecordCreationDto barcodeRecord = new BarcodeRecordCreationDto();
        barcodeRecord.setAttendance(LocalDateTime.now());
        barcodeRecord.setClassSessionReadDto(classSessionDTO);
        barcodeRecord.setStudentReadDto(studentDTO);
        BarcodeRecordReadDto created = barcodeService.create(barcodeRecord);
        return ResponseEntity.created(URI.create("")).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<BarcodeRecord>> fetchAll(Pageable pageable) {
        return ResponseEntity.ok(barcodeService.fetchAll(pageable));
    }

    @GetMapping(value = "/course/{courseId}/student/{studentId}", params = {"page", "size"})
    public ResponseEntity<List<BarcodeRecordReadDto>> getByStudent(@PathVariable int courseId, @PathVariable int studentId, @RequestParam("page") int page,
                                                                   @RequestParam("size") int size) {
        Pageable pageReq = PageRequest.of(page, size);

        Page<BarcodeRecord> barcodeRecordList = barcodeService.getBarcodeByStudentAndClassSession(courseId, studentId, pageReq);
        List<BarcodeRecordReadDto> barcodeRecordReadDtos = barcodeRecordList.stream()
                .map(br -> modelMapper.map(br, BarcodeRecordReadDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(barcodeRecordReadDtos);

    }

    @PostMapping("/checkin")
    public ResponseEntity<String> create(@RequestBody CheckInCreationDto checkInCreationDto) {
        BarcodeRecordCreationDto barcodeRecord = new BarcodeRecordCreationDto();

        StudentReadDto studentReadDto = modelMapper.map(
                studentService.findByBarcode(checkInCreationDto.getStudentBarcode()), StudentReadDto.class);

        if (studentReadDto == null)
            return ResponseEntity.notFound().build();

        ClassSessionReadDto classSessionReadDto = modelMapper.map(
                classSessionService.findById(checkInCreationDto.getClassSessionId()), ClassSessionReadDto.class);

        if (classSessionReadDto == null)
            return ResponseEntity.notFound().build();

        barcodeRecord.setAttendance(LocalDateTime.now());
        barcodeRecord.setClassSessionReadDto(classSessionReadDto);
        barcodeRecord.setStudentReadDto(studentReadDto);

        barcodeService.create(barcodeRecord);

        return ResponseEntity.ok("Checkin Successful for Barcode: " + checkInCreationDto.getStudentBarcode());
    }
}

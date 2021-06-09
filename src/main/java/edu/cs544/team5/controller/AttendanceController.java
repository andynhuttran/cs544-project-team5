package edu.cs544.team5.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.service.AttendanceService;


@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	AttendanceService attendaceService;

	@GetMapping("/{studentId}")
	public List<BarcodeRecord> getStudentAttendance(@PathVariable Integer studentId) {

		return attendaceService.getAttendanceBySudentId(studentId);
	}

	@GetMapping(value="/{studentId}",params="courseId")
	public List<BarcodeRecord> getStudentAttendanceByCourseId(@PathVariable Integer studentId,  @RequestParam Integer courseId ) {

		return attendaceService.getStudentAttendanceByCourseId(studentId, courseId);
	}

	@GetMapping("/{barcodeRecordId}")
	public BarcodeRecord getAttendanceByBarcodeRecordId(@PathVariable Integer barcodeRecordId) {

		return attendaceService.getAttendanceByBarcodeRecordId(barcodeRecordId);
	}

	@PutMapping("/{barcodeRecordId}")
	public BarcodeRecord updateStudentAttendance(@PathVariable Integer barcodeRecordId, 
			@Valid @RequestBody  BarcodeRecord studentAttendance) {

		return attendaceService.updateStudentAttendance(barcodeRecordId, studentAttendance);
	}

	@PatchMapping("/{barcodeRecordId}")
	public BarcodeRecord updateStatus(@PathVariable Integer barcodeRecordId, @Validated @RequestBody BarcodeRecord studentAttendance ) {

		return attendaceService.updateStudentStatus(barcodeRecordId,studentAttendance);
	}
	
	@PostMapping("/scan/{barcode}")
	public void scanBarcode(@PathVariable String barcode) throws ParseException {
		attendaceService.scanBarcode(barcode);
	}
}

package edu.cs544.team5.service;

import java.text.ParseException;
import java.util.List;

import edu.cs544.team5.domain.BarcodeRecord;


public interface AttendanceService {
	


	public List<BarcodeRecord> findAllSudentsAttendnce();

	public List<BarcodeRecord> getAttendanceBySudentId(Integer studentId);
	
	public List<BarcodeRecord> getStudentAttendanceByCourseId(Integer studentId, Integer courseId);

	public BarcodeRecord getAttendanceByBarcodeRecordId(Integer barcodeRecordId);

	public BarcodeRecord updateStudentAttendance(Integer barcodeRecordId, BarcodeRecord dto);

	public BarcodeRecord updateStudentStatus(Integer barcodeRecordId, BarcodeRecord dto);
	
	
	public void scanBarcode(String barcode) throws ParseException;
	
}

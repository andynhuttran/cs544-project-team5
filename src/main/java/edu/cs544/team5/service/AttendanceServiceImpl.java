package edu.cs544.team5.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.CourseSession;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.repository.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	CourseSessionService courseSessionService;
	
	public List<BarcodeRecord> findAllSudentsAttendnce() {
		
		return attendanceRepository.findAll();
	}


	public List<BarcodeRecord> getAttendanceBySudentId(Integer studentId) {
		  return  attendanceRepository.getAttendanceBySudentId(studentId);	

	}
	public List<BarcodeRecord>  getStudentAttendanceByCourseId(Integer studentId, Integer courseId){
		
	return attendanceRepository.getStudentAttendanceByCourseId(studentId,courseId);
	
	}
	
	
	public BarcodeRecord getAttendanceByBarcodeRecordId(Integer barcodeRecordId) {

		return attendanceRepository.getById(barcodeRecordId);
	}


	public BarcodeRecord updateStudentAttendance(Integer barcodeRecordId, BarcodeRecord studentAttendance) {
	
		BarcodeRecord attendanceRecord = attendanceRepository.getById(barcodeRecordId);
		attendanceRecord.setStatus(studentAttendance.getStatus());
		attendanceRecord.setAttendanceDate(studentAttendance.getAttendanceDate());
		attendanceRecord.setId(studentAttendance.getId());
		return attendanceRepository.save(attendanceRecord);
	}

	
	public BarcodeRecord updateStudentStatus(Integer barcodeRecordId, BarcodeRecord studentAttendance) {
		BarcodeRecord attendanceRecord = attendanceRepository.getById(barcodeRecordId);
		attendanceRecord.setStatus(studentAttendance.getStatus());
		return attendanceRepository.save(attendanceRecord);
	}

//  =========================================== //==================================================
 
	public void scanBarcode(String barcode) throws ParseException {		
  		 
 		String morningAttendanceReference = "10:00:00";
 		String afternoonAttendanceReference = "13:30:00";

		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssaaa");

 		 SimpleDateFormat hourFormatter = new SimpleDateFormat("HH:mm:ss");
         
 		 Date attendanceDate = new Date();    
        
         String formattedAttendanceDate = dateFormat.format(attendanceDate);
         String attendanceHour =  hourFormatter.format(attendanceDate);   
         
         int length = formattedAttendanceDate.length();
         
		  String PM_AM = formattedAttendanceDate.substring(length-2, length);
        
          String currentSessionReference =  PM_AM.equals("AM")?morningAttendanceReference:afternoonAttendanceReference; 
         
         long difference_In_Time = hourFormatter.parse(currentSessionReference).getTime()-hourFormatter.parse(attendanceHour).getTime();  
         
      
               
 		Student st = studentService.getStudentByBarcode(barcode);
 		
 	    CourseOffering courseOffering = registrationService.getCourseOffering(st); 
 	    
 		CourseSession cs = courseSessionService.getCourseSession(formattedAttendanceDate,courseOffering);
 		
 		String status = "Present";
 		
 		if(difference_In_Time<0 && difference_In_Time>=-600000)
 			status = "Late";
 		else if(difference_In_Time<-600000)
 			status = "Absent";
 		
 		BarcodeRecord br = new BarcodeRecord();
 		br.setAttendanceDate(attendanceDate);
 		br.setCourseSession(cs);
 		br.setStatus(status);
 		br.setStudent(st);
 		
 		attendanceRepository.save(br);
 	}

}

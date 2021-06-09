package edu.cs544.team5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.cs544.team5.domain.BarcodeRecord;

@Repository
@Transactional
public interface AttendanceRepository extends JpaRepository<BarcodeRecord, Integer> {

	@Query("from BarcodeRecord BR where BR.student.studentId =?1")
	List<BarcodeRecord> getAttendanceBySudentId(Integer studentId);
	
	@Query("Select br from BarcodeRecord br join br.student.registrations.courseOffering co where br.student.studentId=?1 and co.courseId=?2")
	public List<BarcodeRecord>  getStudentAttendanceByCourseId(Integer studentId, Integer courseId);

}

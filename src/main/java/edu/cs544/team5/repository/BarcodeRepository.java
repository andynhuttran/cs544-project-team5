package edu.cs544.team5.repository;

import edu.cs544.team5.domain.BarcodeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface BarcodeRepository extends JpaRepository<BarcodeRecord, Integer>, PagingAndSortingRepository<BarcodeRecord, Integer> {

//    @EntityGraph(attributePaths = {"student", "courseOffering", "classSession"})
//    @Query("select br from BarcodeRecord br join br.student st join br.classSession.courseOffering co" +
//            " where co.id = :courseOfferingId and st.id = :studentId")
//    Page<BarcodeRecord> findByStudent_IdAndClassSession_CourseOffering_Id(@Param("courseOfferingId")int courseOfferingId, @Param("studentId")int studentId,
    Page<BarcodeRecord> findByStudent_IdAndClassSession_CourseOffering_Id(int courseOfferingId, int studentId,
                                     Pageable pageable);
}

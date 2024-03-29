package edu.cs544.team5.repository;

import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Faculty;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Integer> {

    @EntityGraph(attributePaths = {"course", "faculty", "block"})
    @Query("select distinct c from CourseOffering c, Student s inner join s.registrations inner join c.block b " +
            "where b.endDate < current_date and s.id = :id")
    public List<CourseOffering> getPastCourseOffering(@Param("id") int id);

    @EntityGraph(attributePaths = {"course", "faculty", "block"})
    @Query("select distinct c from CourseOffering c, Student s inner join s.registrations inner join c.block b " +
            "where b.beginDate <= current_date and current_date <= b.endDate and s.id = :id")
    public List<CourseOffering> getCurrentCourseOffering(int id);


    @EntityGraph(attributePaths = {"course", "faculty", "block"})
    @Query("select distinct c from CourseOffering c, Student s inner join s.registrations inner join c.block b " +
            "where current_date < b.beginDate and s.id = :id")
    public List<CourseOffering> getFutureCourseOffering(int id);

    @EntityGraph(attributePaths = {"course", "faculty", "block"})
    public List<CourseOffering> findCourseOfferingByIdIn(Collection<Integer> ids);

    List<CourseOffering> findCourseOfferingByFaculty(Faculty faculty);
}

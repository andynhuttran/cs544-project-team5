package edu.cs544.team5.repository;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.domain.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Integer> {
}

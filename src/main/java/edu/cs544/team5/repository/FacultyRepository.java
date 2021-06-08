package edu.cs544.team5.repository;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}

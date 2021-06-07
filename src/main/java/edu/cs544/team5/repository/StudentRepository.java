package edu.cs544.team5.repository;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {


}

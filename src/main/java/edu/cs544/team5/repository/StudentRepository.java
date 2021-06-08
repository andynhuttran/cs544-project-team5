package edu.cs544.team5.repository;

import edu.cs544.team5.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> getStudentById(int id);
    Optional<Student> findByBarcode(String barcode);
}

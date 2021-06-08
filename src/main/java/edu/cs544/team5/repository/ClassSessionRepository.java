package edu.cs544.team5.repository;

import edu.cs544.team5.domain.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ClassSessionRepository extends JpaRepository<ClassSession, Integer> {
}

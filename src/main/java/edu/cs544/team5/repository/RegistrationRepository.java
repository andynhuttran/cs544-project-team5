package edu.cs544.team5.repository;

import edu.cs544.team5.domain.Registration;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
@Transactional
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    @Modifying
    @Query(value = "insert into registration (student_id, offering_id) VALUES (:studentId, :offeringId)", nativeQuery = true)
    public int insert(int studentId, int offeringId);


    @Query(value = "select COUNT(*) from registration where student_id = :studentId and offering_id = :offeringId", nativeQuery = true)
    public int count(int studentId, int offeringId);

}

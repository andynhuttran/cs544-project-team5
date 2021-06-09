package edu.cs544.team5.repository;

import edu.cs544.team5.domain.Location;
import edu.cs544.team5.domain.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TimeSlotRepository extends JpaRepository<Timeslot, Integer> {
}

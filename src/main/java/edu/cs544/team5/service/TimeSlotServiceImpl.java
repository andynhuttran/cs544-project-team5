package edu.cs544.team5.service;

import edu.cs544.team5.domain.Timeslot;
import edu.cs544.team5.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TimeSlotServiceImpl implements AbstractService<Timeslot> {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    @Transactional(readOnly = true)
    public Timeslot findById(int id) {
        return timeSlotRepository.existsById(id) ? timeSlotRepository.getById(id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Timeslot> findAll() {
        return timeSlotRepository.findAll();
    }

    @Override
    public Page<Timeslot> findPaginated(int page, int size) {
        return timeSlotRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Timeslot create(Timeslot Timeslot) {
        return timeSlotRepository.save(Timeslot);
    }

    @Override
    public Timeslot update(Timeslot entity) {
        return timeSlotRepository.save(entity);
    }

    @Override
    public void delete(Timeslot entity) {
        timeSlotRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        if(timeSlotRepository.existsById(entityId))
            timeSlotRepository.deleteById(entityId);
    }
}

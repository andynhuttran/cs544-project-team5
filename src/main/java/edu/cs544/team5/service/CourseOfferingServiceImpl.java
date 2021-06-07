package edu.cs544.team5.service;

import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Faculty;
import edu.cs544.team5.repository.CourseOfferingRepository;
import edu.cs544.team5.repository.CourseRepository;
import edu.cs544.team5.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseOfferingServiceImpl implements AbstractService<CourseOffering> {

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Override
    @Transactional(readOnly = true)
    public CourseOffering findById(int id) {
        return courseOfferingRepository.existsById(id) ? courseOfferingRepository.getById(id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseOffering> findAll() {
        return courseOfferingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CourseOffering> findByFaculty(Faculty faculty){
        return courseOfferingRepository.findCourseOfferingByFaculty(faculty);
    }

    @Override
    public Page<CourseOffering> findPaginated(int page, int size) {
        return courseOfferingRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public CourseOffering create(CourseOffering courseOffering) {
        return courseOfferingRepository.save(courseOffering);
    }

    @Override
    public CourseOffering update(CourseOffering entity) {
        return courseOfferingRepository.save(entity);
    }

    @Override
    public void delete(CourseOffering entity) {
        courseOfferingRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        if(courseOfferingRepository.existsById(entityId))
            courseOfferingRepository.deleteById(entityId);
    }
}

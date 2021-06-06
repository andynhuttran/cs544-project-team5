package edu.cs544.team5.service;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements AbstractService<Course> {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public Course findById(int id) {
        return courseRepository.existsById(id) ? courseRepository.getById(id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> findPaginated(int page, int size) {
        return courseRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Course entity) {
        return courseRepository.save(entity);
    }

    @Override
    public void delete(Course entity) {
        courseRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        if(courseRepository.existsById(entityId))
            courseRepository.deleteById(entityId);
    }
}

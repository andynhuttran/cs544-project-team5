package edu.cs544.team5.service;

import edu.cs544.team5.domain.Course;

import java.util.List;

public interface CourseService {

    public Course findById(Integer id);
    public List<Course> findAll();
    public void saveCourse(Course course);

}

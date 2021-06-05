package edu.cs544.team5;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.service.CourseService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceUnitTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Course> courseList = courseService.findAll();
        Assert.assertEquals(courseList.size(), 3);
    }
}
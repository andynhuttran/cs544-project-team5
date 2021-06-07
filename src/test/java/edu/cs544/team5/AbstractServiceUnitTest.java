package edu.cs544.team5;

import edu.cs544.team5.domain.Course;
import edu.cs544.team5.service.AbstractService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractServiceUnitTest {

    @Autowired
    private AbstractService abstractService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Course> courseList = abstractService.findAll();
        Assert.assertEquals(courseList.size(), 3);
    }
}
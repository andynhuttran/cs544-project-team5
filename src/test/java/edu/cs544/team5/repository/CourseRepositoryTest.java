package edu.cs544.team5.repository;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.domain.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName("Check add new Course")
    void shouldCreateBarcodeRecord() {
        Course course1 = new Course();
        course1.setAbbreviation("CS544");
        course1.setDescription("Enterprise Architecture Description");
        course1.setName("Enterprise Architecture");

        @Nested
        @DisplayName("should")
        class should {
            @Test
            @DisplayName("create and return Course object")
            void createNewCourse() {
                Course newCourse = courseRepository.save(course1);
                assertAll(
                        () -> assertNotNull(newCourse)
                );
            }
        }
    }
}

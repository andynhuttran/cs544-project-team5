package edu.cs544.team5.convertor;

import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.StudentCourseDto;
import edu.cs544.team5.dto.StudentCreationDto;
import org.modelmapper.AbstractConverter;

public class CourseOfferingToStudentCourseDtoConvertor extends AbstractConverter<CourseOffering, StudentCourseDto>  {

    @Override
    protected StudentCourseDto convert(CourseOffering source) {
        StudentCourseDto dto = new StudentCourseDto();

        dto.setCourseName(source.getCourse().getName());
        dto.setCourseDescription(source.getCourse().getDescription());

        dto.setBegin(source.getBlock().getBeginDate());
        dto.setEnd(source.getBlock().getEndDate());

        dto.setSemester(source.getBlock().getSemester());
        dto.setFacultyName(source.getFaculty().getFirstName() + " " + source.getFaculty().getLastName());

        return dto;
    }
}

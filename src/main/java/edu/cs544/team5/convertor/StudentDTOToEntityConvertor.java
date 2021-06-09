package edu.cs544.team5.convertor;

import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.StudentCreationDto;
import org.modelmapper.AbstractConverter;

public class StudentDTOToEntityConvertor extends AbstractConverter<StudentCreationDto, Student> {

    @Override
    protected Student convert(StudentCreationDto source) {
        Student student = new Student();

        student.setStudentId(source.getStudentId());
        student.setFirstName(source.getFirstName());
        student.setLastName(source.getLastName());
        student.setEntryDate(source.getEntryDate());

        student.setUsername(source.getUsername());
        student.setPassword(source.getPassword());

        return student;
    }
}

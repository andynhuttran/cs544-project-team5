package edu.cs544.team5.service;

import edu.cs544.team5.convertor.CourseOfferingToStudentCourseDtoConvertor;
import edu.cs544.team5.convertor.StudentDTOToEntityConvertor;
import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Role;
import edu.cs544.team5.domain.RoleType;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.StudentCourseDto;
import edu.cs544.team5.dto.StudentCreationDto;
import edu.cs544.team5.dto.StudentReadDto;
import edu.cs544.team5.exception.NoSuchElementFoundException;
import edu.cs544.team5.repository.CourseOfferingRepository;
import edu.cs544.team5.repository.StudentRepository;
import edu.cs544.team5.util.BarcodeFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.addConverter(new StudentDTOToEntityConvertor());
        modelMapper.addConverter(new CourseOfferingToStudentCourseDtoConvertor());
    }


    @Override
    public StudentReadDto createStudent(StudentCreationDto dto) {
        //convert dto to entity
        Student studentEntity = modelMapper.map(dto, Student.class);
        studentEntity.setBarcode(BarcodeFactory.getBarcore());
        Role role = roleService.fetchOrInsert(RoleType.STUDENT);
        studentEntity.addRole(role);

        studentRepository.save(studentEntity);

        //return dto
        StudentReadDto studentDTO = modelMapper.map(studentEntity, StudentReadDto.class);
        return studentDTO;
    }

    @Override
    public StudentReadDto findById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("No student available by id=" + id));
        return modelMapper.map(student, StudentReadDto.class);
    }


    @Override
    public List<StudentCourseDto> getPastCourseOffering(int id) {
        List<CourseOffering> courseOfferings = courseOfferingRepository.getPastCourseOffering(id);
        return convertToStudentCourseDto(courseOfferings);
    }

    @Override
    public List<StudentCourseDto> getCurrentCourseOffering(int id) {
        List<CourseOffering> courseOfferings = courseOfferingRepository.getCurrentCourseOffering(id);
        return convertToStudentCourseDto(courseOfferings);
    }

    @Override
    public List<StudentCourseDto> getFutureCourseOffering(int id) {
        List<CourseOffering> courseOfferings = courseOfferingRepository.getFutureCourseOffering(id);
        return convertToStudentCourseDto(courseOfferings);
    }


    private List<StudentCourseDto> convertToStudentCourseDto(List<CourseOffering> courseOfferings) {
        return courseOfferings.stream()
                .map(courseOffering -> modelMapper.map(courseOffering, StudentCourseDto.class))
                .collect(Collectors.toList());
    }

}

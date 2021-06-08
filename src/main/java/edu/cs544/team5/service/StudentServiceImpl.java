package edu.cs544.team5.service;

import edu.cs544.team5.convertor.CourseOfferingToStudentCourseDtoConvertor;
import edu.cs544.team5.convertor.StudentDTOToEntityConvertor;
import edu.cs544.team5.domain.CourseOffering;
import edu.cs544.team5.domain.Role;
import edu.cs544.team5.domain.RoleType;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.*;
import edu.cs544.team5.exception.NoSuchRecordFoundException;
import edu.cs544.team5.exception.StudentHandleException;
import edu.cs544.team5.repository.CourseOfferingRepository;
import edu.cs544.team5.repository.RegistrationRepository;
import edu.cs544.team5.repository.StudentRepository;
import edu.cs544.team5.util.BarcodeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

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
    @Transactional(readOnly = true)
    public StudentReadDto findById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoSuchRecordFoundException("No student available by id=" + id));
        if (student.isActive() == false) {
            throw new StudentHandleException(HttpStatus.NOT_FOUND, "The student is deactivated");
        }

        return modelMapper.map(student, StudentReadDto.class);
    }

    @Override
    public StudentReadDto findByBarcode(String barcode) {
        Student student = studentRepository.findByBarcode(barcode).orElseThrow(() -> new NoSuchRecordFoundException("No student available by barcode=" + barcode));
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

    private Student getStudent(int id){
        Optional<Student> studentOptional = studentRepository.getStudentById(id);
        return studentOptional.orElseThrow(() -> new StudentHandleException(HttpStatus.NOT_FOUND, "Can not found student with id = " + id));
    }

    @Override
    public void activeOrDisableStudent(int id, boolean active) {
        Student student = getStudent(id);

        if (student.isActive() != active){ //change active state
            student.setActive(active);
            studentRepository.save(student);
        }
        else {
            String status = active?"active":"deactivated";
            throw new StudentHandleException(HttpStatus.BAD_REQUEST, "The student have been " + status);
        }
    }


    private List<StudentCourseDto> convertToStudentCourseDto(List<CourseOffering> courseOfferings) {
        return courseOfferings.stream()
                .map(courseOffering -> modelMapper.map(courseOffering, StudentCourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RegistrationReadDto registryCourse(int studentId, RegistrationCreationDto dto) {
        Student student = getStudent(studentId);
        if (student.isActive() == false) {
            throw new StudentHandleException(HttpStatus.NOT_FOUND, "The student is deactivated");
        }

        Arrays.stream(dto.getCourseOfferings()).forEach(offeringId -> {
            registrationRepository.insert(studentId, offeringId);
        });


        List<Integer> list = new ArrayList<>();
        Arrays.stream(dto.getCourseOfferings()).forEach(list::add);
        List<CourseOffering> courseOfferings = courseOfferingRepository.findCourseOfferingByIdIn(list);

        RegistrationReadDto registrationReadDto = new RegistrationReadDto();
        List<StudentCourseDto> courses = convertToStudentCourseDto(courseOfferings);
        registrationReadDto.setCourses(courses);
        registrationReadDto.setDate(LocalDate.now());
        return registrationReadDto;

    }

}

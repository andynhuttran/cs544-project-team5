package edu.cs544.team5.service;

import edu.cs544.team5.domain.Role;
import edu.cs544.team5.domain.RoleType;
import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.StudentCreationDto;
import edu.cs544.team5.dto.StudentReadDto;
import edu.cs544.team5.repository.RoleRepository;
import edu.cs544.team5.repository.StudentRepository;
import edu.cs544.team5.util.BarcodeFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    @Qualifier("studentMapping")
    private ModelMapper modelMapper;


    @Autowired
    private RoleRepository roleRepository;


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
        System.out.println(studentDTO);
        return studentDTO;
    }
}

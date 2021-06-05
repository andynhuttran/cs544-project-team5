package edu.cs544.team5.config;

import edu.cs544.team5.domain.Student;
import edu.cs544.team5.dto.StudentCreationDto;
import edu.cs544.team5.dto.StudentReadDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfiguration {

    @Bean("studentMapping")
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSourceNamingConvention(NamingConventions.NONE)
                .setDestinationNamingConvention(NamingConventions.NONE);

        mappingStudent(modelMapper);
        return modelMapper;
    }


    private void mappingStudent(ModelMapper modelMapper){
        TypeMap<StudentCreationDto, Student> typeMap = modelMapper.createTypeMap(StudentCreationDto.class, Student.class);
        typeMap.addMappings(mapper -> {

            mapper.map(StudentCreationDto::getStudentId, Student::setStudentId);
            mapper.map(StudentCreationDto::getFirstName, Student::setFirstName);
            mapper.map(StudentCreationDto::getLastName, Student::setLastName);
            mapper.map(StudentCreationDto::getEntryDate, Student::setEntryDate);
        });

        modelMapper.typeMap(Student.class, StudentReadDto.class).addMappings(mapper -> {
            mapper.map(Student::getId, StudentReadDto::setId);
            mapper.map(Student::getFirstName, StudentReadDto::setFirstName);
            mapper.map(Student::getLastName, StudentReadDto::setLastName);

            mapper.map(Student::getStudentId, StudentReadDto::setStudentId);
            mapper.map(Student::getEntryDate, StudentReadDto::setEntryDate);
            mapper.map(Student::getBarcode, StudentReadDto::setBarcode);
        });

    }
}

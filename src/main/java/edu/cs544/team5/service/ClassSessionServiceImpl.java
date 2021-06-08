package edu.cs544.team5.service;

import edu.cs544.team5.domain.ClassSession;
import edu.cs544.team5.dto.ClassSessionReadDto;
import edu.cs544.team5.exception.NoSuchRecordFoundException;
import edu.cs544.team5.repository.ClassSessionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class ClassSessionServiceImpl implements ClassSessionService {
    private final ClassSessionRepository classSessionRepository;
    private final ModelMapper modelMapper;

    @Override
    public ClassSessionReadDto findById(Integer id) {
        ClassSession classSession = classSessionRepository.findById(id).orElseThrow(() -> new NoSuchRecordFoundException("No class session available by id=" + id));
        return modelMapper.map(classSession, ClassSessionReadDto.class);
    }
}

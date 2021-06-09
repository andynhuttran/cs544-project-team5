package edu.cs544.team5.service;

import edu.cs544.team5.domain.ClassSession;
import edu.cs544.team5.repository.ClassSessionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
@RequiredArgsConstructor
public class ClassSessionServiceImpl implements ClassSessionService {

    @Autowired
    private ClassSessionRepository classSessionRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public ClassSession findById(int id) {
        return classSessionRepository.existsById(id) ? classSessionRepository.getById(id) : null;
    }


    @Override
    public List<ClassSession> findAll() {
        return classSessionRepository.findAll();
    }

    @Override
    public Page<ClassSession> findPaginated(int page, int size) {
        return null;
    }

    @Override
    public ClassSession create(ClassSession entity) {
        return classSessionRepository.save(entity);
    }

    @Override
    public ClassSession update(ClassSession entity) {
        return classSessionRepository.save(entity);
    }

    @Override
    public void delete(ClassSession entity) {
        classSessionRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        classSessionRepository.deleteById(entityId);
    }
}

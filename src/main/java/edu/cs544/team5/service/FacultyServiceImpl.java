package edu.cs544.team5.service;

import edu.cs544.team5.domain.Faculty;
import edu.cs544.team5.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FacultyServiceImpl implements AbstractService<Faculty> {
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    @Transactional(readOnly = true)
    public Faculty findById(int id) {
        return facultyRepository.existsById(id) ? facultyRepository.getById(id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Page<Faculty> findPaginated(int page, int size) {
        return facultyRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty update(Faculty entity) {
        return facultyRepository.save(entity);
    }

    @Override
    public void delete(Faculty entity) {
        facultyRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        if(facultyRepository.existsById(entityId))
            facultyRepository.deleteById(entityId);
    }
}

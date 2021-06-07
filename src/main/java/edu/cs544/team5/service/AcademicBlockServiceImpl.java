package edu.cs544.team5.service;

import edu.cs544.team5.domain.AcademicBlock;
import edu.cs544.team5.repository.AcademicBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AcademicBlockServiceImpl implements AbstractService<AcademicBlock> {

    @Autowired
    private AcademicBlockRepository academicBlockRepository;

    @Override
    @Transactional(readOnly = true)
    public AcademicBlock findById(int id) {
        return academicBlockRepository.existsById(id) ? academicBlockRepository.getById(id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicBlock> findAll() {
        return academicBlockRepository.findAll();
    }

    @Override
    public Page<AcademicBlock> findPaginated(int page, int size) {
        return academicBlockRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public AcademicBlock create(AcademicBlock AcademicBlock) {
        return academicBlockRepository.save(AcademicBlock);
    }

    @Override
    public AcademicBlock update(AcademicBlock entity) {
        return academicBlockRepository.save(entity);
    }

    @Override
    public void delete(AcademicBlock entity) {
        academicBlockRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        if(academicBlockRepository.existsById(entityId))
            academicBlockRepository.deleteById(entityId);
    }
}

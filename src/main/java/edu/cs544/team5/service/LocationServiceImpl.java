package edu.cs544.team5.service;

import edu.cs544.team5.domain.Location;
import edu.cs544.team5.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements AbstractService<Location> {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Transactional(readOnly = true)
    public Location findById(int id) {
        return locationRepository.existsById(id) ? locationRepository.getById(id) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Page<Location> findPaginated(int page, int size) {
        return locationRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Location create(Location Location) {
        return locationRepository.save(Location);
    }

    @Override
    public Location update(Location entity) {
        return locationRepository.save(entity);
    }

    @Override
    public void delete(Location entity) {
        locationRepository.delete(entity);
    }

    @Override
    public void deleteById(int entityId) {
        if(locationRepository.existsById(entityId))
            locationRepository.deleteById(entityId);
    }
}

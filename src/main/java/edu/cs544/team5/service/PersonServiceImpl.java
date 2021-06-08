package edu.cs544.team5.service;

import edu.cs544.team5.domain.Person;
import edu.cs544.team5.exception.NoSuchRecordFoundException;
import edu.cs544.team5.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    @Transactional(readOnly = true)
    @Override
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username).orElseThrow(() -> new NoSuchRecordFoundException("Username not found"));
    }
}

package edu.cs544.team5.service;

import edu.cs544.team5.domain.Person;
import edu.cs544.team5.dto.PersonCreationDto;
import edu.cs544.team5.dto.PersonReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {
    Person findByUsername(String username);

    PersonReadDto create(PersonCreationDto p);

    Page<Person> fetchAll(Pageable pageable);
}
